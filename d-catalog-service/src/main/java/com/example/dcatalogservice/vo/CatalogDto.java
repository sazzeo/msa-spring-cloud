package com.example.dcatalogservice.vo;

import com.example.dcatalogservice.repository.CatalogEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CatalogDto implements Serializable {
    private String productId;
    private String qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private String userId;

}
