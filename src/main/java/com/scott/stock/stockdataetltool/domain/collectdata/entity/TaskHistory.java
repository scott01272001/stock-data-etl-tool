package com.scott.stock.stockdataetltool.domain.collectdata.entity;

import com.scott.stock.stockdataetltool.domain.VersionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "task_history")
public class TaskHistory extends VersionEntity {

    @Builder.Default
    @Id
    @Column(nullable = false, unique = true)
    private UUID id = UUID.randomUUID();

    @OneToOne(targetEntity = Task.class)
    @PrimaryKeyJoinColumn(name = "task_id")
    private Task task;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String log;

    public enum Status {
        Success, Failed;
    }
}
