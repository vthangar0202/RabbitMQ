package com.vasanth.rabbitmq.exchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HeadersPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();

        String message="message for mobile & TV through headers exchange";
        
        //Leave the routing key as empty
        //add the headers in props
        HashMap<String, Object> headersMap=new HashMap<>();
        headersMap.put("item1", "mobile");
        headersMap.put("item2", "television");
        
        BasicProperties prop=new BasicProperties();
        prop=prop.builder().headers(headersMap).build();
        
        channel.basicPublish("Headers-Exchange", "", prop, message.getBytes());

        // Close the connections
        channel.close();
        connection.close();
    }
}
