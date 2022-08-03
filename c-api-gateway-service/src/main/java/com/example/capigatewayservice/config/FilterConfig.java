package com.example.capigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration  : 어노테이션 주석처리로 필터 끄기
public class FilterConfig {

    //필터 등록하기 : 필터로 라우팅 해보기 => 원래 yml로 등록한거 필터로 등록해보기
    //pre : 컨트롤러 가기 전에
    //post : 컨트롤러 간 후에
  //  @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route( r -> r.path("/first-service/**").filters(
                        f -> f.addRequestHeader("first-request" ,"first-request-value")  //필터 pre 역할
                                .addResponseHeader("first-response" , "first-response-value") //필터 post 역할
                ).uri("http://localhost:8081"))
                .route( r -> r.path("/second-service/**").filters(
                        f -> f.addRequestHeader("second-request" ,"second-request-value")  //필터 pre 역할
                                .addResponseHeader("second-response" , "second-response-value") //필터 post 역할
                ).uri("http://localhost:8082"))
                .build();
    }

}
