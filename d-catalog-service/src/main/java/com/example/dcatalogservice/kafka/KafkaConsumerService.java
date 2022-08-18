package com.example.dcatalogservice.kafka;


import com.example.dcatalogservice.repository.CatalogEntity;
import com.example.dcatalogservice.repository.CatalogRepository;
import com.example.dcatalogservice.vo.RequestCatalog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumerService {

    private final CatalogRepository catalogRepository;
    private final ObjectMapper objectMapper;


    @Transactional //jpa update를 위해 사용
    @KafkaListener(topics = "example-catalog-topic" ,groupId="consumerGroupId")
    public void updateQty(String kafkaMessage)  {
        log.info("KafkaMessage: => {}" , kafkaMessage);

        RequestCatalog requestCatalog = null;

        try {
            requestCatalog = objectMapper.readValue(kafkaMessage, RequestCatalog.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String productId = requestCatalog.getProductId();
        Integer qty = requestCatalog.getQty();

        //일단 상품이 있는가 확인 한후 업데이트를 진행한다.
        CatalogEntity catalogEntity = catalogRepository.findByProductId(productId).orElseThrow(()->new RuntimeException("상품 없음"));

        //수량 정보 업데이트
        catalogEntity.setStock(catalogEntity.getStock()-qty);

    }

}
