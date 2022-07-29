package com.jy.study.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //부트 프로젝트를 유레카 [서버]의 자격으로 등록함
public class EcommerceApplication {

    public static void main(String[] args) {

        SpringApplication.run(EcommerceApplication.class, args);

    }

}
