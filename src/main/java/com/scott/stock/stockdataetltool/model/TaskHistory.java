package com.scott.stock.stockdataetltool.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "task_history")
public class TaskHistory extends VersionedObject {

  @Column(name = "is_success")
  public boolean isSuccess;
  @Id
  @Column(name = "task_history_id")
  @SequenceGenerator(name = "task_history_seq", sequenceName = "task_history_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_history_seq")
  private Long taskHistoryId;
  @OneToOne(targetEntity = ScheduleTask.class)
  @JoinColumn(name = "schedule_task_id")
  private ScheduleTask scheduleTask;

  public TaskHistory(ScheduleTask scheduleTask) {
    this.scheduleTask = scheduleTask;
  }

}
