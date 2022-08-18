package com.example.dorderservice.service;

import com.example.dorderservice.repository.OrderEntity;
import com.example.dorderservice.repository.OrderRepository;
import com.example.dorderservice.vo.OrderDto;
import com.example.dorderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        OrderEntity orderEntity = modelMapper.map(orderDto , OrderEntity.class);
        orderRepository.save(orderEntity);
        return orderDto;
    }

    @Override
    public ResponseOrder findOrderByOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId).orElseThrow(()->new RuntimeException("유저를 찾을 수 없음"));

        return new ResponseOrder(orderEntity);
    }

    @Override
    public List<ResponseOrder> findOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId).stream().map(ResponseOrder::new)
                .collect(Collectors.toList());
    }
}
