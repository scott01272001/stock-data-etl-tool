package com.scott.stock.stockdataetltool.web.controller;

import com.scott.stock.stockdataetltool.model.ScheduleTask;
import com.scott.stock.stockdataetltool.repository.ScheduleTaskRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/schedule-task")
public class ScheduleTaskController {

  private final ScheduleTaskRepository scheduleTaskRepository;

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public List<ScheduleTask> getScheduleTask() {

    return null;
  }

}
