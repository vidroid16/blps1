package com.example.demo.Quartz;

import com.example.demo.Services.Implementations.TestServiceImpl;
import com.example.demo.Services.TestService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
public class MyJob{
    @Autowired
    private TestServiceImpl memberService;
    @Scheduled(fixedRate = 3000000)
    public void execute() {
        System.out.println(memberService.say());
        memberService.mail();
    }
}
