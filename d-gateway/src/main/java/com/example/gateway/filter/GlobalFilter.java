package com.example.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            log.info("Global Filter baseMessage: {}", config.getBaseMessage());

            //Mono객체 => 비동기로 값을 전달할 때 webflux에서 사용하는 데이터 객체 (단일값: mono , 다중값 : flux)
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {  //비동기 환경이기때문에 동적 파라미터로 호출함
                if (config.isPostLogger()) {
                    log.info("Custom Filter End : response code -> {}", response.getStatusCode());
                }
            }));
        }));
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

}
