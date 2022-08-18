package com.example.dorderservice.kafka.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Fields {

    private String type;
    private Boolean optional;
    private String field;

}
