package com.scott.stock.stockdataetltool.repository;

import com.scott.stock.stockdataetltool.StockDataEtlToolApplicationTests;
import com.scott.stock.stockdataetltool.model.ScheduleTask;
import com.scott.stock.stockdataetltool.model.ScheduleTask.TaskStatus;
import com.scott.stock.stockdataetltool.model.vo.StockPriceTaskMetadata;
import com.scott.stock.stockdataetltool.model.vo.StockPriceTaskMetadata.RetrieveDate;
import com.scott.stock.stockdataetltool.model.vo.StockPriceTaskMetadata.TargetType;
import com.scott.stock.stockdataetltool.model.vo.TaskLifecycle;
import com.scott.stock.stockdataetltool.model.vo.TaskLifecycle.LaunchTime;
import com.scott.stock.stockdataetltool.model.vo.TaskLifecycle.Lifecycle;
import com.scott.stock.stockdataetltool.model.vo.TaskMetadata;
import java.time.ZonedDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleTaskRepositoryTest extends StockDataEtlToolApplicationTests {

  @Autowired
  private ScheduleTaskRepository scheduleTaskRepository;

  @Test
  public void queryTest() {
    List<ScheduleTask> result = scheduleTaskRepository.findAll();
    System.out.println(result);
    result.forEach(r -> System.out.println(r.getCreateDatetime()));
  }

  @Test
  public void insertTest() {
    TaskLifecycle taskLifecycle = new TaskLifecycle();
    taskLifecycle.setLifecycle(Lifecycle.Once);
    taskLifecycle.setLaunchTime(LaunchTime.builder().hour(12).minute(12).build());

    TaskMetadata taskMetadata = StockPriceTaskMetadata.builder()
        .targetType(TargetType.All)
        .retrieveDate(RetrieveDate.builder().month(1).day(25).build())
        .build();

    ScheduleTask scheduleTask = new ScheduleTask("test-task", TaskStatus.Running, taskLifecycle, taskMetadata);
    scheduleTaskRepository.save(scheduleTask);
  }

  @Test
  public void test() {
    System.out.println(ZonedDateTime.now());
  }


}
