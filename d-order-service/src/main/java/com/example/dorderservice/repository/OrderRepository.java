package com.example.dorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity ,Long> {


    Optional<OrderEntity> findByOrderId(String OrderId);

    //유저당 주문 정보 여러개
    List<OrderEntity> findByUserId(String UserId);
}
