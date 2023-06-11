package com.microservice.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducer {
    private final AmqpTemplate amqpTemplate;

    public void publish(Object payload,String exchange,String routingKey){
        log.info("publishing to {} using routingKey {}. payload {}",exchange,routingKey,payload);
        amqpTemplate.convertAndSend(exchange,routingKey,payload);
        log.info("published to {} using routingKey {}. payload {}",exchange,routingKey,payload);

    }
}
