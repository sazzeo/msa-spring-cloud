package com.example.dorderservice.controller;


import com.example.dorderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order-service")
public class OrderController {

    private final OrderService orderService;


    //주문추가
    @PostMapping("/{userId}/orders")
    public ResponseEntity order(@PathVariable String userId) {
        return null;
    }

    //주문
    @GetMapping("/{userId}/orders")
    public ResponseEntity findOrder(@PathVariable String userId) {
       // orderService.findOrderByUserId(userId);
        return null;
    }


}
