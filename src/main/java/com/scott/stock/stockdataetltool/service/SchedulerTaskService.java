package com.scott.stock.stockdataetltool.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
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


}
