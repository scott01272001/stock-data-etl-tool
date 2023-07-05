package com.scott.stock.stockdataetltool.model;

import com.scott.stock.stockdataetltool.model.vo.TaskMetadata;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "schedule_task")
public class ScheduleTask extends VersionedObject {

  @Id
  @Column(name = "schedule_task_id")
  @SequenceGenerator(name = "schedule_task_id_seq", sequenceName = "schedule_task_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_task_id_seq")
  private Long scheduleTaskId;

  @Column(name = "task_name")
  private String taskName;

  @Column(name = "task_status")
  @Enumerated(EnumType.STRING)
  private TaskStatus taskStatus;

  @Column(name = "task_metadata")
  private TaskMetadata taskMetadata;

  public ScheduleTask(String taskName, TaskStatus taskStatus) {
    this.taskName = taskName;
    this.taskStatus = taskStatus;
  }

  public enum TaskStatus {
    Running, Stop;
  }

}
