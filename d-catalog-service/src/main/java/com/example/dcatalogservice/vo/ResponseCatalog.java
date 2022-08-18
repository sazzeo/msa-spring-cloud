package com.example.dcatalogservice.vo;

import com.example.dcatalogservice.repository.CatalogEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName; //name 추가
    private Integer unitPrice;

    private Integer stock;
    private Date createdAt; //date 추가


    public ResponseCatalog(CatalogEntity catalogEntity) {
        this.productId = catalogEntity.getProductId();
        this.productName = catalogEntity.getProductName();
        this.unitPrice = catalogEntity.getUnitPrice();
        this.createdAt = catalogEntity.getCreatedAt();
        this.stock = catalogEntity.getStock();
    }
}

