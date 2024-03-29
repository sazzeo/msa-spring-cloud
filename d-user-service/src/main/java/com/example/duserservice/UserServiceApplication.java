package com.example.duserservice;

import com.example.duserservice.error.FeignErrorDecoder;
import feign.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignErrorDecoderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient  //유레카 클라이언트로 추가
@EnableFeignClients  //openfeign 사용을 위해 추가
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);  //매칭전략, 엄격하게 : 이름이 100% 맞아야만 매핑시킴
        return modelMapper;
    }


    @Bean
    @LoadBalanced  //url 주소 service이름으로 대체 시켜주는 어노테이션
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    //Feign Client의 로거를 찍기 위해서 등록함.
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


}
