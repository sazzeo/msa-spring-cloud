package com.example.dcatalogservice.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestCatalog {

    private String productId;
    private Integer qty;
    private Integer totalPrice;
    private String orderId;
    private String userId;

}
