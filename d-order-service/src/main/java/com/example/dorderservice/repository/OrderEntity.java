package com.example.dorderservice.repository;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="order")
public class OrderEntity {

    @Id
    private Long id;
}
