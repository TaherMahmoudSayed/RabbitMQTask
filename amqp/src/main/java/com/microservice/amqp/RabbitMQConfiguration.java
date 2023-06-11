package com.microservice.amqp;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;
@Configuration
@RequiredArgsConstructor
public class RabbitMQConfiguration {
    private final ConnectionFactory connectionFactory;
// rabbit template that allows us to convert message and sent it to exchange
    @Bean
    //rabbit sender or publisher
    public AmqpTemplate amqpTemplate(){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter());
        return rabbitTemplate;
    }
    @Bean
    // simple rabbit listener
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory rabbitListener=
                new SimpleRabbitListenerContainerFactory();
        rabbitListener.setConnectionFactory(connectionFactory);
        rabbitListener.setMessageConverter(jacksonConverter());
        return rabbitListener;
    }
    @Bean
    public MessageConverter jacksonConverter(){
        Jackson2JsonMessageConverter jacksonMessageConverter= new Jackson2JsonMessageConverter();
        return jacksonMessageConverter;

    }

}