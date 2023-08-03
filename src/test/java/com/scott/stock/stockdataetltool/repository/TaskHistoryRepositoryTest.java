package com.scott.stock.stockdataetltool.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskHistoryRepositoryTest extends ScheduleTaskRepositoryTest {

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;
    @Autowired
    private ScheduleTaskRepository scheduleTaskRepository;

    @Test
    public void test() {

    }

}
