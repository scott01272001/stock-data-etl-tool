package com.scott.stock.stockdataetltool.repository;

import com.scott.stock.stockdataetltool.StockDataEtlToolApplicationTests;
import com.scott.stock.stockdataetltool.model.ScheduleTask;
import com.scott.stock.stockdataetltool.model.ScheduleTask.TaskStatus;
import com.scott.stock.stockdataetltool.model.vo.StockPriceTaskMetadata;
import com.scott.stock.stockdataetltool.model.vo.StockPriceTaskMetadata.RetrieveDate;
import com.scott.stock.stockdataetltool.model.vo.StockPriceTaskMetadata.TargetType;
import com.scott.stock.stockdataetltool.model.vo.TaskLifecycle;
import com.scott.stock.stockdataetltool.model.vo.TaskLifecycle.LaunchTime;
import com.scott.stock.stockdataetltool.model.vo.TaskLifecycle.Lifecycle;
import com.scott.stock.stockdataetltool.model.vo.TaskMetadata;
import com.scott.stock.stockdataetltool.repository.query.ScheduleTaskSpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;


public class ScheduleTaskRepositoryTest extends StockDataEtlToolApplicationTests {

    @Autowired
    private ScheduleTaskRepository scheduleTaskRepository;

    @Test
    public void queryTest() {
        List<ScheduleTask> result = scheduleTaskRepository.findAll();
        System.out.println(result);
        result.forEach(r -> System.out.println(r.getCreateDatetime()));
    }

    //@Test
    public void insertTest() {
        TaskLifecycle taskLifecycle = new TaskLifecycle();
        taskLifecycle.setLifecycle(Lifecycle.Once);
        taskLifecycle.setLaunchTime(LaunchTime.builder().hour(12).minute(12).build());

        TaskMetadata taskMetadata = StockPriceTaskMetadata.builder()
            .targetType(TargetType.All)
            .retrieveDate(RetrieveDate.builder().month(1).day(25).build())
            .build();

        ScheduleTask scheduleTask = new ScheduleTask("test-task", TaskStatus.Running, taskLifecycle,
            taskMetadata);
        scheduleTaskRepository.save(scheduleTask);
    }

    //@Test
    public void queryWithClause() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Specification<ScheduleTask> spec = ScheduleTaskSpec.where()
            .datetimeBetween(sdf.parse("2023-07-12 21:30:00"), sdf.parse("2023-07-12 21:50:00"))
            .build();

        List<ScheduleTask> result = scheduleTaskRepository.findAll(spec);
        result.forEach(System.out::println);
    }

}
