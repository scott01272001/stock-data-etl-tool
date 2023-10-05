package com.scott.stock.stockdataetltool.domain.collectdata.vo;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskConfig {

    private CollectTargetMode collectTargetMode;

    private Set<String> targets;

    public enum CollectTargetMode {
        All, Specific;
    }

}
