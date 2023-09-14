package com.scott.stock.stockdataetltool.service;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class SchedulerTaskService {

    private final Scheduler scheduler;

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("Fail to start scheduler. " + e.getMessage());
        }
    }

    @PreDestroy
    public void stop() {
        try {
            scheduler.shutdown(true);
        } catch (SchedulerException e) {
            log.error(e);
        }
    }

    public void launchTask(Class<? extends Job> jobClass, String jobName, String jobGroup,
        Map<String, Object> metadata) {
        JobDetail job = newJob(jobClass)
            .withIdentity(jobName, jobGroup)
            .usingJobData(new JobDataMap(metadata))
            .build();

        Trigger trigger = newTrigger()
            .withIdentity(jobName, jobGroup)
            .startNow()
            .withSchedule(simpleSchedule()
                .withRepeatCount(0))
            .build();

        try {
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            log.error("launch task fail, msg: {}", e.getMessage());
        }
    }

}
