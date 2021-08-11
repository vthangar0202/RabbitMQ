package com.vasanth.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class JsonConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallBack = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            JSONObject jsonMessage = new JSONObject(message);
            System.out.println("message : " + jsonMessage.toString());
        };

        channel.basicConsume("Queue-1", true, deliverCallBack, consumerTag -> {
        });

    }

}
