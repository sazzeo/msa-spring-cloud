# Micro Service 간 통신
``` text
Kafka 이용하기
```

## 다른 service 끼리 kafka 통신
![image](https://user-images.githubusercontent.com/89112466/185573316-9431fb4c-8942-4e5b-9bff-99073c00d291.png)
- sink 와 source를 service 내부의 kafka class로 구현함
- kafka connector는 구동되어있어야함

## 동일 레플리카 service 끼리 kafka통신
![image](https://user-images.githubusercontent.com/89112466/185573329-56414499-0de9-4385-b675-60de4d8cc6f1.png)
- kafka connector 이용
- kafka sink로 db에 직접 데이터 insert
  - kafka sink 정보
  ```yaml
  {
    "name": "my-order-sink-connect",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSinkConnector",
        "connection.url": "jdbc:postgresql://spring-boot-study.cchzqclxtrke.ap-northeast-2.rds.amazonaws.com:5432/postgres",
        "connection.user": "db id",
        "connection.password": "db pw",
        "auto.create": "true",
        "auto.evolve": "true",
        "table.name.format":"msa_study.${topic}",
        "delete.enabled": "false",
        "tasks.max": "1",
        "topics": "orders"
    }
  }
  ```

- 전송 data 포맷
![image](https://user-images.githubusercontent.com/89112466/185574197-3b59d68c-4a8b-420d-9db8-9991c1a7e436.png)
