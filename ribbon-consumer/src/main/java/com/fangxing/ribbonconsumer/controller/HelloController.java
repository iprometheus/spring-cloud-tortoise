package com.fangxing.ribbonconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/ribbon-consumer")
    public String helloConsumer(){
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }

    @HystrixCommand(fallbackMethod = "helloHystrixFallback")
    @GetMapping(value = "/hello-hystrix")
    public String hello(){
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }

    public String helloHystrixFallback(){
        return "hello-hystrix-error";
    }

    @HystrixCommand(fallbackMethod = "delayFallback")
    @GetMapping(value = "delay")
    public String testDelay(){
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello-delay",String.class).getBody();
    }


    public String delayFallback(){
        return "hello-hystrix-delay";
    }
}
