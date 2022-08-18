package com.example.duserservice.controller;


import com.ctc.wstx.util.StringUtil;
import com.example.duserservice.vo.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserStatusController {


    private Environment env;


    @Autowired
    private Greeting greeting;

    @Autowired
    public UserStatusController(Environment env) {
        this.env = env;
    }


    @GetMapping("/welcome2")
    public String welcome2() {
        return greeting.getMessage();
    }



}
