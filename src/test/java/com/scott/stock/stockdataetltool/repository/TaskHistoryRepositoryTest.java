package com.scott.stock.stockdataetltool.repository;

import com.scott.stock.stockdataetltool.model.ScheduleTask;
import com.scott.stock.stockdataetltool.model.ScheduleTask.TaskStatus;
import com.scott.stock.stockdataetltool.model.TaskHistory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskHistoryRepositoryTest extends ScheduleTaskRepositoryTest {

  @Autowired
  private TaskHistoryRepository taskHistoryRepository;
  @Autowired
  private ScheduleTaskRepository scheduleTaskRepository;

  @Test
  public void test() {
    ScheduleTask scheduleTask = new ScheduleTask("test-task", TaskStatus.Running);
    scheduleTask = scheduleTaskRepository.save(scheduleTask);

    TaskHistory taskHistory = new TaskHistory(scheduleTask);
    taskHistoryRepository.save(taskHistory);

    List<TaskHistory> r = taskHistoryRepository.findAll();
    r.forEach(a -> {
      System.out.println(a);
      System.out.println(a.getScheduleTask());
    });

  }

}
