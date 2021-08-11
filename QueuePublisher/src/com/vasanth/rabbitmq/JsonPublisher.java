package com.vasanth.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class JsonPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        
        ConnectionFactory factory=new ConnectionFactory();
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        
        //Construct json message
        JSONObject message=new JSONObject();
        message.put("task-id", "123");
        message.put("name", "xxx");
        message.put("email", "xyz@abc.com");
        message.put("query", "select * from employee");
        
        //Publish
        channel.basicPublish("", "Queue-1", null, message.toString().getBytes());
        
        channel.close();
        connection.close();
    }
}
