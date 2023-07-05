package com.scott.stock.stockdataetltool.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock extends SerializeObject {

  @Id
  @Column(name = "stock_id")
  private Long stockId;

  @Column
  private String name;

}
