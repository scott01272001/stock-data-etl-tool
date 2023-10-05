package com.scott.stock.stockdataetltool.domain.collectdata.vo;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    private boolean runRepeat;
    private ZonedDateTime startAt;
    private long repeatIntervalInMinute;
}
