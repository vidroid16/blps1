package com.example.demo.Services.Implementations;

import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.ProjectsDB.ProjectRepository;
import com.example.demo.DataBase.UsersDB.User;
import com.example.demo.DataBase.UsersDB.UsersRepository;
import com.example.demo.Quartz.Mail.MailSender;
import com.example.demo.Services.TestService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    MailSender mailSender;
    @Override
    public String say() {
        return "I am test service. Everything is OK";
    }
    @Override
    public void use() {
        Project u =  projectRepository.findByName("Dota3");
        int sum = u.getCur_sum();
        System.out.println(sum);
        projectRepository.save(new Project("TestShit", 1337, 228));
    }

    @Override
    public void mail() {
        for (User u:usersRepository.getall()) {
            mailSender.send("Kickstarter: Лучие проекты за неделю для вас!!", "У нас 1 проект вы о чем вообще! Но зато есть анекдот", u.getLogin());
        }
    }
}
