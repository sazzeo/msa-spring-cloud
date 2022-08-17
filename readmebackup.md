# MSA Spring Cloud

## branch 정리

### 순서

1. eureka
2. zuul
3. gateway
4. gateway-eureka
5. msa-toy-project/1
6. config-server/actuator
7. config-server/cloud-bus
8. encrypt
9. microservice-communication/rest-template



### 1. eureka
- netflix eureka로 로드밸런싱

### 2. zuul
- gateway 등장 전 netflix zuul 사용
- 동기 통신만 지원함

### 3. gateway
- 비동기 통신을 지원하는 spring cloud gateway 구축하기

### 4. gateway-eureka
- gateway와 eureka 통합해보기 
- 포트번호가 아닌 service 이름으로 접근

### 5. msa-toy-project/1
- 1~4 종합으로 작은 ecommerce service 만들어보기

### 6. config-server/actuator
- spring-cloud-config로 config server 구축하기

### 7. config-server/cloud-bus
- cloud-bus rabbitmq 실습

### 8. encrypt
- yml 파일간 암호화
- 대칭키, 비대칭키 이용

### 9. microservice-communication/rest-template
- rest-template으로 microservice간 호출