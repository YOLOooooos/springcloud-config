package com.zhai.springcloun.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    public String serverPort;
    @GetMapping(value = "/payment/consul")
    public String paymentConsul(){
        return "springcloun with consul:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
