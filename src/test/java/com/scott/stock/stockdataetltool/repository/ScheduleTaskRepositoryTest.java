package com.scott.stock.stockdataetltool.repository;

import com.scott.stock.stockdataetltool.StockDataEtlToolApplicationTests;
import com.scott.stock.stockdataetltool.model.ScheduleTask;
import com.scott.stock.stockdataetltool.model.ScheduleTask.TaskStatus;
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
    ScheduleTask scheduleTask = new ScheduleTask("test-task", TaskStatus.Running);
    scheduleTaskRepository.save(scheduleTask);
  }

  @Test
  public void test() {
    System.out.println(ZonedDateTime.now());
  }


}
