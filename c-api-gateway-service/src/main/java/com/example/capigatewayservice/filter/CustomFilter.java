package com.example.capigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Custom Pre Filter 등록

        //exchange객체 : 비동기 환경에서 request와 response객체를 얻어주는 객체. (비동기 환경에서는 이 두 객체를 쓸 수가 없음)
        return ((exchange, chain) -> {
            ServerHttpRequest request =exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom PRE filter -> {}", request.getId());

            // Custom Post(후속) Filter로 이동 시키기
            return chain.filter(exchange).then(Mono.fromRunnable(()->{ //단일값 전달할 때 쓰는 mono객체
                log.info("Custom Post Filter: response code -> {}" , response.getStatusCode());
            }));
        });
    }


    //내부 클래스
    public static class Config {
        //config 정보가 있는 경우 여기에 등록
    }
    
}
