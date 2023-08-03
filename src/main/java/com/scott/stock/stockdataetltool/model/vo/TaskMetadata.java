package com.scott.stock.stockdataetltool.model.vo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, property = "type", visible = true, include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = StockPriceTaskMetadata.class, name = "Stock_Price")
})
public class TaskMetadata {
  protected TaskType type;

  public TaskMetadata() {
  }

  public TaskType getType() {
    return type;
  }

  public void setType(TaskType type) {
    this.type = type;
  }

  public enum TaskType {
    Stock_Price;
  }

}
