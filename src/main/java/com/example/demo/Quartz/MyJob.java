package com.example.demo.Quartz;
import com.example.demo.Services.Implementations.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
public class MyJob {
    @Autowired
    private TestServiceImpl memberService;
    public void execute() {
        System.out.println(memberService.say());
        memberService.mail();
    }
}
