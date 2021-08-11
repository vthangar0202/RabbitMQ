package com.vasanth.spring.rabbitmq.listener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.vasanth.spring.rabbitmq.dto.Person;

@Service
public class HeadersExchangeConsumer {

    @RabbitListener(queues="Mobile")
    public void getMessage(byte[] message) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis=new ByteArrayInputStream(message);
        ObjectInput in=new ObjectInputStream(bis);
        Person p=(Person)in.readObject();
        System.out.println("In headers exchange -> Name: "+p.getName());
    }
}
