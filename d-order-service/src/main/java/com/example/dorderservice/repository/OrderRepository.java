package com.example.dorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity ,Long> {

    Optional<OrderEntity> findByUserId(String UserId);
}
