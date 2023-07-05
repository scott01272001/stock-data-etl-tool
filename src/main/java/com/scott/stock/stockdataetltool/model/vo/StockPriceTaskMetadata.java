package com.scott.stock.stockdataetltool.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
public class StockPriceTaskMetadata extends TaskMetadata {
  private TargetType targetType;
  private List<String> stockNumber;
  private RetrieveDate retrieveDate;

  public enum TargetType {
    All, Specific;
  }

  @Builder
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class RetrieveDate {
    private int month;
    private int day;
  }


}
