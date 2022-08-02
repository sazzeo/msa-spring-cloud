package com.example.zuulclientsecondservice.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/second-service")
public class SecondServiceController {

    @GetMapping("/welcome")
    public String welcom() {
        return  "welcome to the Second service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader(value = "second-request" , required = false) String headerValue) {
        log.info(headerValue);
        return "Hello World In Second Service";
    }
}
