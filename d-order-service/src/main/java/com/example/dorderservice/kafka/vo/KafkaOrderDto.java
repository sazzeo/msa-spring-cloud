package com.example.dorderservice.kafka.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class KafkaOrderDto implements Serializable {

    private Schema schema;
    private Payload payload;

    public KafkaOrderDto() {
        init();
    }

    public KafkaOrderDto(Payload payload) {
        init();
        this.payload = payload;
    }


    public void init() {
        List<Fields> fields = List.of(new Fields("string" , true , "order_id")
                , new Fields("string" , true , "user_id")
                , new Fields("string" , true , "product_id")
                , new Fields("int64" , true , "qty")
                , new Fields("int64" , true , "total_price")
                , new Fields("int64" , true , "unit_price")
        );
        this.schema = Schema.builder()
                .type("struct")
                .optional(false)
                .fields(fields)
                .name("orders")
                .build();

    }


}
