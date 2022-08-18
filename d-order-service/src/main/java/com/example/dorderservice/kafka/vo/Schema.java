package com.example.dorderservice.kafka.vo;


import lombok.Builder;
import lombok.Data;

import java.util.List;

//카프카가 db에 전송할 포맷 설정
@Data
public class Schema {

    private String type;
    private List<Fields> fields;
    private boolean optional;
    private String name;

    @Builder
    public Schema(String type, List<Fields> fields, boolean optional, String name) {
        this.type = type;
        this.fields = fields;
        this.optional = optional;
        this.name = name;
    }
}
