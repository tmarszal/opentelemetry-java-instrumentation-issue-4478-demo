package com.example.demo;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.opentelemetry.api.trace.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);

    private final QueueMessagingTemplate qmt;

    public Controller(AmazonSQSAsync sqs) {
        this.qmt = new QueueMessagingTemplate(sqs);
    }

    @GetMapping("/send")
    public void send() {
        logger.info("Sending SQS message, traceId: {}", Span.current().getSpanContext().getTraceId());
        qmt.convertAndSend("test-queue", "test message");
    }

    @GetMapping("/receive")
    public void receive() {
        String payload = qmt.receiveAndConvert("test-queue", String.class);
        logger.info("Received message: {}, traceId: {}", payload, Span.current().getSpanContext().getTraceId());
    }

}
