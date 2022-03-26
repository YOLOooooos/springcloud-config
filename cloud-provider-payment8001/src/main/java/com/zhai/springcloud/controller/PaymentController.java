package com.zhai.springcloud.controller;


import com.zhai.springcloud.service.PaymentService;
import com.zhai.springcloud.entities.CommonResult;
import com.zhai.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);

        log.info("*********插入结果*********"+result);
        if (result > 0){
            return new CommonResult(200,"插入成功，端口为："+serverPort,result);
        }else {
            return new CommonResult(400,"插入数据库失败");
        }
    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult query(@PathVariable("id") Long id){
        Payment payment = paymentService.getPayment(id);
        log.info("*********查询结果*********"+payment);
        if (payment != null){
            return new CommonResult(200,"查询成功,端口为"+serverPort,payment);
        }else {
            return new CommonResult(400,"查询无返回记录"+id);
        }

    }
    @GetMapping(value = "/patment/discovery")
    public Object discover(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("**********elment:"+element);
        }
        /**
         * 根据微服务名称获取当前所有实例
         */
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;


    }
    /**
     * 轮询
     */
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;//返回服务接口
    }

    /**
     *
     */
    @GetMapping(value = "payment/feign/timeout")
    public String paymentFeignTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return serverPort;
    }
}
