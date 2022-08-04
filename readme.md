## Gateway + Eureka
<hr />

```
게이트웨이와 유레카로 로드밸런싱 해보기
```

1. Eureka Server 
2. Gateway 
3. Service 1
4. Service 2

### Eureka Server
<hr/>

- ecommerce
<br/>

### Gateway
<hr/>

- c-api-gateway-service
<br/>
>yml 파일에 유레카 service name으로 표기한다.
```
//application.yml

uri: lb://ZUUL-CLIENT-FIRST-SERVICE
uri: lb://ZUUL-CLIENT-SECOND-SERVICE
```

### Service
<hr/>
1. b-zuul-client-first-service <br/>
2. b-zuul-client-second-service