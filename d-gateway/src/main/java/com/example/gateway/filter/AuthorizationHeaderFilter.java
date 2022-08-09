package com.example.gateway.filter;

import com.example.gateway.security.JwtTokenParser;
import com.example.gateway.vo.UserDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final JwtTokenParser jwtTokenParser;

    AuthorizationHeaderFilter(JwtTokenParser jwtTokenParser) {
        super(Config.class);
        this.jwtTokenParser = jwtTokenParser;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange , chain )->{

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            //만약 헤더에 authorization 정보가 없는 경우?
            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(response , "no authorization header" , HttpStatus.UNAUTHORIZED);
            }

            String bearerToken = (String) request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            if(!jwtTokenParser.isBearerToken(bearerToken)) {
                return onError(response , "no authorization header" , HttpStatus.UNAUTHORIZED);
            }

            String token = jwtTokenParser.getToken(bearerToken);

            if(!jwtTokenParser.isValidToken(token)) {
                return onError(response , "no authorization header" , HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        });

    }


    //Mono 객체 : webflux에서 데이터 전달하는 객체임
    //여기서 설정하는 httpstatus가 실제 response 되는 status가 됨.
    private Mono<Void> onError( ServerHttpResponse response , String err, HttpStatus httpStatus)  {
        response.setStatusCode(httpStatus);
        log.error(err);
        return response.setComplete();
    }


    @Data
    public static class Config {}
}
