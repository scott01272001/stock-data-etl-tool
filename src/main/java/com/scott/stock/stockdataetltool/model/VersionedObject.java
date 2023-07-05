package com.scott.stock.stockdataetltool.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@MappedSuperclass
public class VersionedObject extends SerializeObject {

  @Column(name = "create_datetime", columnDefinition = "TIMESTAMP WITH TIME ZONE")
  @CreationTimestamp
  protected OffsetDateTime createDatetime;

  @Column(name = "update_datetime", columnDefinition = "TIMESTAMP WITH TIME ZONE")
  @UpdateTimestamp
  protected OffsetDateTime updateDatetime;


  public LocalDateTime getCreateDatetime() {
    return createDatetime.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
  }

  public void setCreateDatetime(LocalDateTime createDatetime) {
    this.createDatetime = createDatetime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
  }

  public LocalDateTime getUpdateDatetime() {
    return updateDatetime.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
  }

  public void setUpdateDatetime(LocalDateTime updateDatetime) {
    this.updateDatetime = updateDatetime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
  }

}
