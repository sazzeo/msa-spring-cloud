package com.jy.study.zuul.zuulclientfirstservice.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/first-service")  //Api Gateway에서 실행시
//@RequestMapping("/")    // Zuul에서 실행시
public class FirstServiceController {



    Environment env ;  //yml파일에 있는 정보 가져오기

    @Autowired
    public FirstServiceController(Environment env) {
        this.env = env;
    }
    
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
    public String check(HttpServletRequest request) {
        log.info("Server port= {}" ,  request.getServerPort());
        return "Hi!! Firsts Service " + "<br/> server port: " + env.getProperty("local.server.port") ;
    }
}
