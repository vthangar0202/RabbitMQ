package com.vasanth.rabbitmq.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();

        String message="message for mobile & AC through topic exchange";
        //Message will be published based on the Routing key
        channel.basicPublish("Topic-Exchange", "tv.mobile.ac", null, message.getBytes());

        // Close the connections
        channel.close();
        connection.close();
    }

}
