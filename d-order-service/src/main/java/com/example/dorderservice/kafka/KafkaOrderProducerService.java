package com.example.dorderservice.kafka;

import com.example.dorderservice.kafka.vo.KafkaOrderDto;
import com.example.dorderservice.kafka.vo.Payload;
import com.example.dorderservice.vo.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaOrderProducerService {

    //order service => postgre 로 데이터 전송
    private final KafkaTemplate kafkaTemplate;
    private final ObjectMapper objectMapper;



    //전송
    public OrderDto send(String topic, OrderDto orderDto)  {

        KafkaOrderDto kafkaOrderDto = new KafkaOrderDto(new Payload(orderDto));

        String json = "";
        try {
            json = objectMapper.writeValueAsString(kafkaOrderDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        kafkaTemplate.send(topic , json);
        log.info("Order Producer sent data from the Order MicroService " + kafkaOrderDto) ;

        return orderDto;

    }


}
