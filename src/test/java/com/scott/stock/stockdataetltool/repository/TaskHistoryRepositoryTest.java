package com.scott.stock.stockdataetltool.repository;

import com.scott.stock.stockdataetltool.StockDataEtlToolApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskHistoryRepositoryTest extends StockDataEtlToolApplicationTests {

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;
    @Autowired
    private ScheduleTaskRepository scheduleTaskRepository;

    @Test
    public void test() {
        System.out.println("----test----");
    }

}
