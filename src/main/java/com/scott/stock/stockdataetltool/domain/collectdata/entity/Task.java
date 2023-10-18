package com.scott.stock.stockdataetltool.domain.collectdata.entity;

import com.scott.stock.stockdataetltool.domain.VersionEntity;
import com.scott.stock.stockdataetltool.domain.collectdata.vo.Schedule;
import com.scott.stock.stockdataetltool.domain.collectdata.vo.TaskConfig;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "task")
public class Task extends VersionEntity {

    @Id
    @Column(nullable = false, unique = true)
    @SequenceGenerator(name = "task_id_seq", sequenceName = "task_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @Column(nullable = false)
    private boolean activate;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Schedule schedule;

    @Column(name = "task_config")
    @JdbcTypeCode(SqlTypes.JSON)
    private TaskConfig taskConfig;

    public enum TaskType {
        StockInfo, StockPrice;
    }

}