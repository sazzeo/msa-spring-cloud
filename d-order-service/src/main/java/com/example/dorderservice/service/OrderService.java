package com.example.dorderservice.service;

import com.example.dorderservice.vo.OrderDto;
import com.example.dorderservice.vo.ResponseOrder;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);
    ResponseOrder findOrderByOrderId(String orderId);

    List<ResponseOrder> findOrdersByUserId(String UserId);

}
