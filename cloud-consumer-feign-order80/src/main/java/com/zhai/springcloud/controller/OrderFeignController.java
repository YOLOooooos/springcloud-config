package com.zhai.springcloud.controller;

import com.zhai.springcloud.entities.CommonResult;
import com.zhai.springcloud.entities.Payment;
import com.zhai.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "consumer/payment/get/{id}")
    public CommonResult<Payment> query(@PathVariable("id") Long id){
        return paymentFeignService.getId(id);
    }

    @GetMapping(value = "consumer/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        return paymentFeignService.paymentFeignTimeOut();

    }
}
