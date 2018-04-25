package com.fangxing.helloservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RestController
public class HelloController {


    @Autowired
    private DiscoveryClient client;

    @GetMapping(value = "/hello")
    public String sayHello(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request.getRequestURL().toString());
        return "hello world!"+client.getLocalServiceInstance().getHost();
    }


    @GetMapping(value = "/hello-delay")
    public String testDelay() throws Exception{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request.getRequestURL().toString());
        Thread.sleep(new Random().nextInt(5000));
        return "hello delay";
    }
}
