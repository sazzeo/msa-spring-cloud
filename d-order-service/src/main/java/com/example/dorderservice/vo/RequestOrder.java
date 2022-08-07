package com.example.dorderservice.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestOrder {

    private String productId;

    private Integer qty;  //수량

    private Integer unitPrice;

    private Integer totalPrice;

    private String userId;
}
