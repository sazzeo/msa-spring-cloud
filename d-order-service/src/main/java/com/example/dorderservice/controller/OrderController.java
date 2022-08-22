package com.example.dorderservice.controller;


import com.example.dorderservice.kafka.KafkaCatalogProducerService;
import com.example.dorderservice.kafka.KafkaOrderProducerService;
import com.example.dorderservice.service.OrderService;
import com.example.dorderservice.vo.OrderDto;
import com.example.dorderservice.vo.RequestOrder;
import com.example.dorderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class OrderController {

    private final OrderService orderService;

    private final KafkaCatalogProducerService kafkaCatalogProducerService;

    private final KafkaOrderProducerService kafkaOrderProducerService;

    private final ModelMapper modelMapper;

    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("Catalog Service :: port = %s" , env.getProperty("local.server.port"));
    }

    //주문추가
    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> addOrder(@PathVariable String userId
            , @RequestBody RequestOrder requestOrder) {
        requestOrder.setUserId(userId);
        log.info("Before retrieved added microservice");
        OrderDto orderDto = modelMapper.map(requestOrder , OrderDto.class);
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());

        //catalog 정보 업데이트
//        kafkaCatalogProducerService.send("example-catalog-topic" , orderDto);

        //order 정보 kafka로 업데이트
//        kafkaOrderProducerService.send("orders" ,orderDto);

        /* jpa 이용하는 코드*/
        orderService.createOrder(orderDto);


        ResponseOrder responseOrder = modelMapper.map(orderDto , ResponseOrder.class );
        log.info("After retrieved added microservice");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    //주문 확인
    @GetMapping("/{userId}/orders")
    public ResponseEntity< List<ResponseOrder>> findOrder(@PathVariable String userId)  throws Exception{
        log.info("Before retrieved orders microservice");
        List<ResponseOrder> responseOrder = orderService.findOrdersByUserId(userId);
        log.info("After retrieved orders microservice");

        try {

            Thread.sleep(1000);
            throw new Exception("장애발생");
        } catch (InterruptedException e) {
            log.warn(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseOrder);
    }

}
