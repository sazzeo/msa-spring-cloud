package com.example.duserservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserStatusController {


    private Environment env;


    @Autowired
    public UserStatusController(Environment env) {
        this.env = env;
    }




}
