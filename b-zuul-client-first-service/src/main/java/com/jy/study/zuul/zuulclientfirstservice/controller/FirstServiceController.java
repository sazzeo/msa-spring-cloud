package com.jy.study.zuul.zuulclientfirstservice.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/first-service")  //Api Gateway에서 실행시
//@RequestMapping("/")    // Zuul에서 실행시
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcom() {
        return  "welcome to the First service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String headerValue) {
       log.info(headerValue);
        return "Hello World In First Service";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi!! Firsts Service";
    }
}
