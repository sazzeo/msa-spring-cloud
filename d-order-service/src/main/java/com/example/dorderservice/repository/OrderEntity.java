package com.example.dorderservice.repository;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 120)
    private String productId;

    @Column(nullable = false)
    private Integer qty;  //수량

    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;

    
    //오더 아이디 유니크
    @Column(nullable = false , unique = true)
    private String orderId;

    @Column(nullable = false , updatable = false , insertable = false)
    @ColumnDefault(value="CURRENT_TIMESTAMP")
    private Date createdAt;

}
