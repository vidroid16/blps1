package com.example.demo.socket;

import com.example.demo.DataBase.ProjectsDB.ProjectRepository;
import com.example.demo.Services.DataService;
import com.example.demo.Services.Implementations.DataServiceImpl;
import com.example.demo.Services.Implementations.DonationServiceImpl;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@EnableRabbit
@Component
@ConditionalOnProperty(
        value = "is_listen",
        havingValue = "true"
)
public class SocketReceiver {

    @Autowired
    DataServiceImpl dataService;
    @Value("is_listen")
    String isListenStr;

    @Autowired
    private ProjectRepository projectRepository;

    @RabbitListener(queues = "messages", containerFactory = "")
    public void listener(String s){
        boolean isListen = Boolean.parseBoolean(isListenStr);
        System.out.println("Listened: "+ s);
        System.out.println("isListen = " + isListen);
        JSONObject object = new JSONObject(s);
        Long id = object.getLong("content");
        System.out.println("moderID == "+id);
        dataService.moderate(id);
    }
}