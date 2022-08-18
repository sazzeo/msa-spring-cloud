package com.example.dorderservice.kafka.vo;


import com.example.dorderservice.vo.OrderDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class Payload {

    //snake case
    private String order_id;
    private String user_id;
    private String product_id;
    private Integer qty;
    private Integer total_price;
    private Integer unit_price;

    @Builder
    public Payload(OrderDto orderDto) {
        this.order_id = orderDto.getOrderId();
        this.user_id = orderDto.getUserId();
        this.product_id = orderDto.getProductId();
        this.qty = orderDto.getQty();
        this.total_price = orderDto.getTotalPrice();
        this.unit_price =orderDto.getUnitPrice();
    }



}
