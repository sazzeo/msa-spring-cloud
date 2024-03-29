package com.example.duserservice.client;

import com.example.duserservice.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// controller 처럼 작성하는 인터페이스를 body 없이 작성함.

@FeignClient(name="order-service")
public interface OrderServiceClient {


    //feign Client에서는 PathVariable옵션에 value를 지정해줘야함..
    @GetMapping("/{userId}/orders")
    List<ResponseOrder> getOrders(@PathVariable(value="userId") String userId);

}
