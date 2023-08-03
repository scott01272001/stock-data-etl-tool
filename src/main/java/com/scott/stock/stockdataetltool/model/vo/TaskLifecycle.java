package com.scott.stock.stockdataetltool.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_EMPTY)
public class TaskLifecycle {
  private Lifecycle lifecycle;
  @Valid
  private LaunchTime launchTime;
  @Min(value = 1, message = "dayOfWeek must greater then 1")
  @Max(value = 7, message = "dayOfWeek must less then 7")
  private Integer dayOfWeek;
  @Min(value = 1, message = "dayOfMonth must greater then 0")
  @Max(value = 31, message = "dayOfMonth must less or equal to 31")
  private Integer dayOfMonth;

  public enum Lifecycle {
    Once, Day, Week, Month;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class LaunchTime {
    @Min(value = 0, message = "LaunchTime hour must greater or equal to 0")
    @Max(value = 23, message = "LaunchTime hour must less then 24")
    private int hour;
    @Min(value = 0, message = "LaunchTime minute must greater or equal to 0")
    @Max(value = 59, message = "LaunchTime minute must less then 60")
    private int minute;
  }

}
