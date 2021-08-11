package com.vasanth.spring.rabbitmq.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vasanth.spring.rabbitmq.dto.Person;

@RestController
@RequestMapping("/api/v1")
public class PublisherController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    
    @GetMapping("/test/{name}")
    public String testAPI(@PathVariable("name") String name) {
        Person person = new Person(1L, name);
        
        // Publish message to a queue
        rabbitTemplate.convertAndSend("Mobile", person);
        
        // Direct exchange
        rabbitTemplate.convertAndSend("Direct-Exchange", "mobile", person);
        
        //Fanout
        rabbitTemplate.convertAndSend("Fanout-Exchange", "", person);
        
        //Topic
        rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", person);
        
        return "success";
    }
    
    //Publisher for Headers Exchange
    @GetMapping("/test-header/{name}")
    public String testHeadersExchangeAPI(@PathVariable("name") String name) throws IOException {
        Person person = new Person(1L, name);
        
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream out=new ObjectOutputStream(bos);
        out.writeObject(person);
        out.flush();
        out.close();
        
        byte[] byteMessage=bos.toByteArray();
        bos.close();
        
        Message message=MessageBuilder.withBody(byteMessage)
                .setHeader("item1", "mobile")
                .setHeader("intem2", "television").build();
        rabbitTemplate.send("Headers-Exchange", "", message);;
       
        return "success";
    }

}
