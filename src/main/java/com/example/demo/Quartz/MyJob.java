package com.example.demo.Quartz;
import com.example.demo.DataBase.UsersDB.User;
import com.example.demo.DataBase.UsersDB.UsersRepository;
import com.example.demo.Quartz.Mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class MyJob{
    @Autowired
    private UsersRepository usersRepository;
    @Scheduled(fixedRate = 30000)
    public void execute() {
        MailSender mailSender = new MailSender();
        for (User u:usersRepository.getall()) {
            mailSender.send(mailSender.getGmailSession(),"Kickstarter: Лучие проекты за неделю для вас!!", "У нас 1 проект вы о чем вообще! Но зато есть анекдот", u.getLogin());
        }
    }
}
