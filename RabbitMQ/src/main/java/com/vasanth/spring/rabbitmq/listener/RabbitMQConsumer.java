package com.vasanth.spring.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.vasanth.spring.rabbitmq.dto.Person;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues="TV")
    public void getMessage(Person p) {
        System.out.print("Name : "+ p.getName());
    }
}
