package com.example.capigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//동기방식의 톰캣이 아닌 비동기 방식의 netty WAS 로 올라감
//api 게이트웨이에서 실행시 yml 파일의 prefix가 타 서비스 controller 와 일치해야함.
@SpringBootApplication
public class CApiGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CApiGatewayServiceApplication.class, args);
    }

}
