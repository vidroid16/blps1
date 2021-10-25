package com.example.demo.Quartz;
import com.example.demo.Services.Implementations.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class MyJob{
    @Autowired
    private TestServiceImpl memberService;
    @Scheduled(fixedRate = 30000)
    public void execute() {
        System.out.println(memberService.say());
        memberService.mail();
    }
}
