# Spring Cloud Config 

``` text
spring cloud config 로 yml파일 한번에 관리하기 
```
1. e-config-service
2. d-user-service
3. d-gateway-service
4. ecommerce (Eureka Server) 

## RabbitMq 설치 가이드

[rabbitmq 설치 가이드 in Notion](https://absorbing-brain-061.notion.site/Rabbit-MQ-c093020f8d614a738e8903d3cd29878c)

## 공통 Dependencies

```text
implementation 'org.springframework.boot:spring-boot-starter-actuator:2.7.2'
implementation 'org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.3'
implementation 'org.springframework.cloud:spring-cloud-starter-bus-amqp:3.1.2'
```

- amqp : 메시지 지향 미들웨어용 개방형 표준 응용 프로토콜
  - rabbitmq 사용을 위해 추가함

## yml 파일 설정

```yaml
spring:
	rabbitmq:  
	    host: 127.0.0.1
	    port: 5672 
	    username: guest
	    password: guest

management:
  endpoints:
    web:
      exposure:
        include:  busrefresh
```

- rabbitMq server 주소와 정보 입력
- busrefresh : 전체파일 refresh용 endpoint