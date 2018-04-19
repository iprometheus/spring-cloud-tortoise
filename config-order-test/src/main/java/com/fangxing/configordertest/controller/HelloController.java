package com.fangxing.configordertest.controller;

import com.fangxing.configordertest.config.EnvConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    EnvConfig config;

    @GetMapping(value = "/hello")
    public String sayHello(){
        return config.getJAVA_HOME();
    }

}
