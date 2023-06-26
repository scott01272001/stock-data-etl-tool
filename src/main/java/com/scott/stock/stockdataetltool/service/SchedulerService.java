package com.scott.stock.stockdataetltool.service;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import com.scott.stock.stockdataetltool.job.HelloWorldJob;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class SchedulerService {

    private final Scheduler scheduler;

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("Fail to start scheduler. " + e.getMessage());
        }

        JobDetail job = newJob(HelloWorldJob.class)
            .withIdentity("myJob", "group1")
            .setJobData()
            .usingJobData()
            .build();

        // Trigger the job to run now, and then every 40 seconds
        Trigger trigger = newTrigger()
            .withIdentity("myTrigger", "group1")
            .startNow()
            .withSchedule(simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever())
            .build();

        // Tell quartz to schedule the job using our trigger
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            log.error("fail to run job. " + e.getMessage());
        }
    }

    @PreDestroy
    public void shutdown() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error("Fail to stop scheduler. " + e.getMessage());
        }
    }


}
