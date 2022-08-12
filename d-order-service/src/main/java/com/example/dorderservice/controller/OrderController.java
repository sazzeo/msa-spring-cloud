package com.example.dorderservice.controller;


import com.example.dorderservice.service.OrderService;
import com.example.dorderservice.vo.OrderDto;
import com.example.dorderservice.vo.RequestOrder;
import com.example.dorderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class OrderController {

    private final OrderService orderService;

    private final ModelMapper modelMapper;

    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("Catalog Service :: port = %s" , env.getProperty("local.server.port"));
    }

    //주문추가
    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> order(@PathVariable String userId
            , @RequestBody RequestOrder requestOrder) {
        requestOrder.setUserId(userId);

        OrderDto orderDto = modelMapper.map(requestOrder , OrderDto.class);
        orderService.createOrder(orderDto);

        ResponseOrder responseOrder = modelMapper.map(orderDto , ResponseOrder.class );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    //주문 확인
    @GetMapping("/{userId}/orders")
    public ResponseEntity< List<ResponseOrder>> findOrder(@PathVariable String userId) {
        List<ResponseOrder> responseOrder = orderService.findOrdersByUserId(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseOrder);
    }

}
