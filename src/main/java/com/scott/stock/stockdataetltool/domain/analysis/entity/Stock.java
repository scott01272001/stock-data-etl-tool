package com.scott.stock.stockdataetltool.domain.analysis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Stock {

    @Id
    @Column
    @SequenceGenerator(name = "stock_id_seq", sequenceName = "stock_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_id_seq")
    private long id;

    @Column(name = "industry_category", nullable = false)
    private String industryCategory;

    @Column(name = "stock_id", nullable = false, unique = true)
    private String stockId;

    @Column(name = "stock_name", nullable = false, unique = true)
    private String stockName;

    @Column(nullable = false)
    private String type;

}
