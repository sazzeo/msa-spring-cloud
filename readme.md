# Micro Service 간 통신
``` text
RestTemplate 이용하기
```
- userService에서 RestTemplate 이용
- @LoadBalancer 사용으로 url을 간략화 할 수 있다.

### Bean 등록
```java
    @Bean
    @LoadBalanced 
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
```

### url 사용

- 기존: http://lcoalhost:8000/order-service/*
  - 게이트웨이 거친 버전 /order-service가 붙음
  
- 변경후 : http://ORDER-SERVICE/*
  - 유레카에서 가져온 버전  http://localhost:0/* 와 동일함   
  - /order-service가 제거 됨