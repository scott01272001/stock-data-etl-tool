package com.scott.stock.stockdataetltool.web.dto;

import com.scott.stock.stockdataetltool.model.ScheduleTask.TaskStatus;
import com.scott.stock.stockdataetltool.model.vo.TaskLifecycle;
import com.scott.stock.stockdataetltool.model.vo.TaskMetadata;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleTaskDto {
  private String name;
  private TaskStatus status;
  @Valid
  private TaskLifecycle taskLifecycle;
  private TaskMetadata taskMetadata;

}
