package com.scott.stock.stockdataetltool.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@MappedSuperclass
public class VersionEntity extends SerializeObject {

    @Column(name = "create_datetime", columnDefinition = "timestamp with time zone default now()")
    @CreationTimestamp
    protected LocalDateTime createDatetime;

    @Column(name = "update_datetime", columnDefinition = "timestamp with time zone default now()")
    @UpdateTimestamp
    @Version
    protected LocalDateTime updateDatetime;

}
