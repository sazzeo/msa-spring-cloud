# Spring Cloud Config 

``` text
spring cloud config 로 yml파일 한번에 관리하기 
```
1. e-config-service
2. d-user-service
3. d-gateway-service
4. ecommerce (Eureka Server) 

## Config

1. e-config-service

```text
- Spring Cloud Config Server 의존성 추가
- application.yml 파일에 remote / local git repository 주소 등록 설정
```


## service

1. d-user-service
2. d-gateway-service


```text
- Spring Cloud Config 의존성 추가
- actuator , bootstrap 의존성 추가
- config-server 정보를 bootstrap.yml 파일에 추가
- actuator endpoint 정보를 application.yml 파일에 추가 
```

