package com.vasanth.rabbitmq.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FanoutPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();

        String message="message for mobile & AC through fanout";
        //Leave the queue name as empty. as the fanout will broadcast the message to all the queues.
        channel.basicPublish("Fanout-Exchange", "", null, message.getBytes());

        // Close the connections
        channel.close();
        connection.close();
    }

}
