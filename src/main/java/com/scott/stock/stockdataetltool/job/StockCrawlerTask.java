package com.scott.stock.stockdataetltool.job;

import com.scott.stock.stockdataetltool.model.ScheduleTask;
import com.scott.stock.stockdataetltool.repository.ScheduleTaskRepository;
import com.scott.stock.stockdataetltool.repository.TaskHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.ObjectNotFoundException;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Log4j2
@RequiredArgsConstructor
public class StockCrawlerTask implements Job {
  public static final String Key = "TASK_ID";

  private final TaskHistoryRepository taskHistoryRepository;
  private final ScheduleTaskRepository scheduleTaskRepository;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    try {
      JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
      long taskId = jobDataMap.getLongValue(Key);
      ScheduleTask scheduleTask = scheduleTaskRepository.findById(taskId)
          .orElseThrow(() -> new ObjectNotFoundException(taskId, "ScheduleTask"));



    } catch (Exception e) {

    }

  }

}
