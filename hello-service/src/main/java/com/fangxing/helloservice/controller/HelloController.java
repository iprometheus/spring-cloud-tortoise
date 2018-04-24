package com.fangxing.helloservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController {


    @Autowired
    private DiscoveryClient client;

    @GetMapping(value = "/hello")
    public String sayHello(){
        return "hello world!"+client.getLocalServiceInstance().getHost();
    }


    @GetMapping(value = "/hello-delay")
    public String testDelay() throws Exception{
        Thread.sleep(new Random().nextInt(5000));
        return "hello delay";
    }
}
