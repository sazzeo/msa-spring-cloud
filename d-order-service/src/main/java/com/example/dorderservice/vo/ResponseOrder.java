package com.example.dorderservice.vo;

import com.example.dorderservice.repository.OrderEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createAt;
    private String orderId;

    public ResponseOrder(OrderEntity orderEntity) {
        this.productId = orderEntity.getProductId();
        this.qty = orderEntity.getQty();
        this.unitPrice = orderEntity.getUnitPrice();
        this.totalPrice = orderEntity.getTotalPrice();
        this.createAt = orderEntity.getCreatedAt();
        this.orderId = orderEntity.getOrderId();
    }
}
