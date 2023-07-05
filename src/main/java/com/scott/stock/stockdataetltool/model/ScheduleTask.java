package com.scott.stock.stockdataetltool.model;

import com.scott.stock.stockdataetltool.model.vo.TaskLifecycle;
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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

  @Column(name = "name")
  private String name;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private TaskStatus status;

  @Column(name = "lifecycle", columnDefinition = "jsonb")
  @JdbcTypeCode(SqlTypes.JSON)
  private TaskLifecycle lifecycle;

  @Column(name = "metadata", columnDefinition = "jsonb")
  @JdbcTypeCode(SqlTypes.JSON)
  private TaskMetadata taskMetadata;

  public ScheduleTask(String name, TaskStatus status, TaskLifecycle lifecycle, TaskMetadata taskMetadata) {
    this.name = name;
    this.status = status;
    this.lifecycle = lifecycle;
    this.taskMetadata = taskMetadata;
  }

  public enum TaskStatus {
    Running, Stop;
  }

}
