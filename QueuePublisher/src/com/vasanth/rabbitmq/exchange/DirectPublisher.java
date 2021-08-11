package com.vasanth.rabbitmq.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author Vasanth
 * Example of Publishing the message to the specific queue through direct exchange
 */
public class DirectPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        // Create connection and Channel
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Message
        String message = "This is Mobile";

        // Create the Exchange and bind the queue in RabbitMQ Management
        channel.basicPublish("Direct-Exchange", "mobile", null, message.getBytes());

        // Close the connections
        channel.close();
        connection.close();
    }

}
