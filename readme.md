# MSA Toy Project

```
MSA Toy Project만들어보기
```

1. d-user-service
2. d-order-service
3. d-gateway-service
4. ecommerce (Eureka Server) 

## Eureka Server


- ecommerce
<br/>

## Gateway


- c-api-gateway-service

<br/>

>yml 파일에 유레카 service name으로 표기한다.

```
//application.yml

uri: lb://ZUUL-CLIENT-FIRST-SERVICE
uri: lb://ZUUL-CLIENT-SECOND-SERVICE
```

## Service

1. b-zuul-client-first-service <br/>
2. b-zuul-client-second-service
