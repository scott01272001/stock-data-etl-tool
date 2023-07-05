package com.scott.stock.stockdataetltool.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class SchedulerService {

    private final Scheduler scheduler;

//    @PostConstruct
//    public void init() throws SchedulerException {
//        try {
//            scheduler.start();
//        } catch (SchedulerException e) {
//            log.error("Fail to start scheduler. " + e.getMessage());
//        }
//
//        scheduler.getListenerManager().addJobListener(new HellWorldListener(),
//            keyEquals(jobKey("myJob", "group1")));
//
//        scheduler.getListenerManager().addTriggerListener(new HelloWorldTriggerListener());
//
//        JobDetail job = newJob(HelloWorldJob.class)
//            .withIdentity("myJob", "group1")
//            .usingJobData("count", 1)
//            .build();
//
//        // Trigger the job to run now, and then every 40 seconds
//        Trigger trigger = newTrigger()
//            .withIdentity("myTrigger", "group1")
//            .startNow()
//            .withSchedule(simpleSchedule()
//                .withIntervalInSeconds(5)
//                .repeatForever())
//            .build();
//
//        // Tell quartz to schedule the job using our trigger
//        try {
//            scheduler.scheduleJob(job, trigger);
//        } catch (SchedulerException e) {
//            log.error("fail to run job. " + e.getMessage());
//        } catch (RuntimeException e) {
//            log.error(e.getMessage());
//        }
//    }

//    @PreDestroy
//    public void shutdown() {
//        try {
//            scheduler.shutdown();
//        } catch (SchedulerException e) {
//            log.error("Fail to stop scheduler. " + e.getMessage());
//        }
//    }


}
