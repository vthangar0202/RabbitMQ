package com.vasanth.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        
        //Create connection and Channel
        ConnectionFactory factory=new ConnectionFactory();
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        
        //Message
        String message="First message from RabbitMQ";
        
        //Create the Queue "Queue-1" in RabbitMQ Management
        // It will be redirected to the queue through "Default Exchange"
        channel.basicPublish("", "Queue-1", null, message.getBytes());
        
        //Close the connections
        channel.close();
        connection.close();
    }

}
