package com.scott.stock.stockdataetltool.domain.analysis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "industry_category")
public class IndustryCategory {

    @Id
    @Column
    @SequenceGenerator(name = "industry_category_id_seq", sequenceName = "industry_category_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "industry_category_id_seq")
    private long id;

    @Column(nullable = false, unique = true)
    private String type;
}
