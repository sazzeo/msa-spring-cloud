# Micro Service 간 통신
``` text
OpenFeign 이용하기
```
- userService에서 FeingClient를 이용한다.
- @FeignClient 인터페이스를 작성한다.

## @FeignClient 인터페이스 작성

```java
@FeignClient(name="order-service")
public interface OrderServiceClient {

  @GetMapping("/{userId}/orders")
  List<ResponseOrder> getOrders(@PathVariable String userId);

}
```

## 사용

- 빈으로 주입받아 사용한다.

```java
  private final OrderServiceClient orderServiceClient;
  //중략
  List<ResponseOrder> orders = orderServiceClient.getOrders;

```

## 에러처리

- ErrorDecoder interface를 구현한다
- 이 인터페이스를 Bean으로 등록하면 ExceptionAdvice처럼 동작한다.

- [ErrorDecoder구현 클래스 확인하기](https://github.com/sazzeo/msa-spring-cloud/blob/master/d-user-service/src/main/java/com/example/duserservice/error/FeignErrorDecoder.java)