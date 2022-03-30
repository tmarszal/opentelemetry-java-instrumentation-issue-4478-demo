To run SQS mock locally:
```shell
java -Dconfig.file=elasticmq/custom.conf -jar elasticmq/elasticmq-server-1.3.3.jar
```

To run demo application:
```shell
mvn spring-boot:run
```

To trigger message:
```shell
curl -ki http://localhost:8080/send
```

