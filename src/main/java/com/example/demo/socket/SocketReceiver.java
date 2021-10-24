package com.example.demo.socket;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class SocketReceiver {
    @RabbitListener(queues = "messages")
    public void listener(String s){
        System.out.println("Listened: "+ s);
    }
}