package com.example.dcatalogservice.config;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    //접속 정보 등록
    @Bean
    public ConsumerFactory<String , String> consumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , "127.0.0.1:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG , "consumerGroupId"); //카프카에서 컨슈머들을 그룹화 시킬 수 있는데 그때 사용하는 그룹.
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG , StringDeserializer.class);  //역직렬화 할 클래스
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG , StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(properties);
    }


    //실제 topics이 들어오면 바라보고 있다가 실행시킬 이벤트 리스너 => 위 접속 정보를 set인자로 받아서 등록한다.
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String , String> concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory
                = new ConcurrentKafkaListenerContainerFactory<>() ;
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());

        return kafkaListenerContainerFactory;
    }
}
