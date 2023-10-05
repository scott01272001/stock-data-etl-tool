package com.scott.stock.stockdataetltool.domain.collectdata;

import com.scott.stock.stockdataetltool.StockDataEtlToolApplicationTests;
import com.scott.stock.stockdataetltool.domain.collectdata.entity.Task;
import com.scott.stock.stockdataetltool.domain.collectdata.entity.Task.TaskType;
import com.scott.stock.stockdataetltool.domain.collectdata.vo.Schedule;
import com.scott.stock.stockdataetltool.domain.collectdata.vo.TaskConfig;
import com.scott.stock.stockdataetltool.infrastructure.databse.repository.TaskRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskRepositoryTest extends StockDataEtlToolApplicationTests {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void createTest() {
        Task task = Task.builder().type(TaskType.StockInfo).activate(true)
            .schedule(Schedule.builder().build())
            .taskConfig(TaskConfig.builder().build()).build();
        task = taskRepository.save(task);
        List<Task> taskList = taskRepository.findAll();
        System.out.println(taskList);
    }


}
