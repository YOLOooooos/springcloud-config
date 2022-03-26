package com.zhai.springcloud.service;

import com.zhai.springcloud.entities.CommonResult;
import com.zhai.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Configuration
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getId(@PathVariable("id") Long id);

    @GetMapping(value = "payment/feign/timeout")
    public String paymentFeignTimeOut();
}
