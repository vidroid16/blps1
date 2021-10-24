package com.example.demo.Quartz;

import com.example.demo.Services.Implementations.TestServiceImpl;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzSubmitJobs {
    private static final String CRON_EVERY_FIVE_MINUTES = "0 0/5 * ? * * *";

    @Bean(name = "memberStats")
    public JobDetailFactoryBean jobMemberStats() {
        return QuartzConfig.createJobDetail(MyJob.class, "Testing Quartz");
    }

    @Bean(name = "memberStatsTrigger")
    public SimpleTriggerFactoryBean triggerMemberStats(@Qualifier("memberStats") JobDetail jobDetail) {
        return QuartzConfig.createTrigger(jobDetail, 3000000, "Doing Quartz Test");
    }


}