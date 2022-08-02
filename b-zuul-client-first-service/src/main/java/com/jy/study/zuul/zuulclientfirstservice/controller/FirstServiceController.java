package com.jy.study.zuul.zuulclientfirstservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcom() {
        return  "welcome to the First service";
    }
}
