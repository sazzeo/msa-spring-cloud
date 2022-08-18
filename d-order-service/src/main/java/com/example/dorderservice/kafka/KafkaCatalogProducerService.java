package com.example.dorderservice.kafka;

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
public class KafkaCatalogProducerService {

    private final KafkaTemplate kafkaTemplate;
    private final ObjectMapper objectMapper;

    //전송
    public OrderDto send(String topic, OrderDto orderDto) {
        String OrderJson = "";
        try {
             OrderJson= objectMapper.writeValueAsString(orderDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic , OrderJson);
        log.info("Kafka Producer sent data from the Order microservice: " + orderDto) ;

        return orderDto;

    }


}
