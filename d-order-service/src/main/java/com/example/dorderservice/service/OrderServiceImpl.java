package com.example.dorderservice.service;

import com.example.dorderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl {

    private final OrderRepository orderRepository;


}
