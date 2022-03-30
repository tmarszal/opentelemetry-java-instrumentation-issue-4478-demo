package com.example.demo;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.extension.annotations.WithSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Listener {
    Logger logger = LoggerFactory.getLogger(Listener.class);

    @SqsListener("test-queue")
    @WithSpan
    public void listener(@Payload String payload) {
        logger.info("Received message: {}, traceId: {}", payload, Span.current().getSpanContext().getTraceId());
    }

}
