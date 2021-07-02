package com.example.demo.Quartz;

import com.example.demo.Services.Implementations.TestServiceImpl;
import com.example.demo.Services.TestService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
@Slf4j
public class MyJob implements Job {
    @Autowired
    private TestServiceImpl memberService;
    @Override
    public void execute(JobExecutionContext context) {
        log.info("Job ** {} ** starting @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
        System.out.println(memberService.say());
        memberService.mail();
        log.info("Job ** {} ** completed.  Next job scheduled @ {}", context.getJobDetail().getKey().getName(), context.getNextFireTime());
    }
}
