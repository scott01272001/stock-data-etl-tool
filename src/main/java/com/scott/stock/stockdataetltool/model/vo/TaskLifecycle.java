package com.scott.stock.stockdataetltool.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_EMPTY)
public class TaskLifecycle {
  private Lifecycle lifecycle;
  private LaunchTime launchTime;
  @Size(min = 1, max = 7)
  private Integer dayOfWeek;
  @Size(min = 1, max = 31)
  private Integer dayOfMonth;

  public enum Lifecycle {
    Once, Day, Week, Month;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class LaunchTime {
    private int hour;
    private int minute;
  }

}
