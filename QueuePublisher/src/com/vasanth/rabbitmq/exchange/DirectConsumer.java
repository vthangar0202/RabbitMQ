package com.vasanth.rabbitmq.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class DirectConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        
        ConnectionFactory factory=new ConnectionFactory();
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();

        DeliverCallback deliverCallBack=(consumerTag,delivery)->{
            String message=new String(delivery.getBody());
            System.out.println("message : "+message);
        };
        
        channel.basicConsume("Mobile", true, deliverCallBack, consumerTag->{});
    }

}
