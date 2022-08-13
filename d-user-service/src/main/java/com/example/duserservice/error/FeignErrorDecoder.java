package com.example.duserservice.error;


import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


//feign client의 에러를 처리해주는 클래스
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 400 :
                break;
            case 404:
                //methodKey = feignClient interface 정의한 메소드명
                if(methodKey.contains("getOrders")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()) , "주문 url이 잘못되었습니다.");
                }
                break;
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
