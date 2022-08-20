package com.example.duserservice.config;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Resilience4JConfig {

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
//        return factory -> factory.configureDefault(id->new Resilience4JConfigBuilder(id)
//                .timeLimiterConfig()
//                .circuitBreakerConfig(circuitBreakerConfig)
//                .build());
//
//    }

}
