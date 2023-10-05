//package com.scott.stock.stockdataetltool.application.service;
//
//import static org.quartz.JobBuilder.newJob;
//import static org.quartz.TriggerBuilder.newTrigger;
//
//import com.scott.stock.stockdataetltool.application.dto.CollectStockListRequest;
//import com.scott.stock.stockdataetltool.domain.collectdata.CollectStockInfo;
//import java.util.HashMap;
//import java.util.Map;
//import lombok.RequiredArgsConstructor;
//import org.quartz.JobDataMap;
//import org.quartz.JobDetail;
//import org.quartz.JobKey;
//import org.quartz.Scheduler;
//import org.quartz.Trigger;
//import org.quartz.TriggerKey;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class StockListCollectorManager {
//
//    private static String COLLECT_STOCK_LIST = "collect-stock-list";
//
//    private final Scheduler scheduler;
//
//    public void createTask(CollectStockListRequest request) {
//        JobKey jobKey = JobKey.jobKey(COLLECT_STOCK_LIST);
//
//        Map<String, Object> metadata = new HashMap<>();
//        metadata.put("dataset", "TaiwanStockInfo");
//
//        JobDetail job = newJob(CollectStockInfo.class)
//            .withIdentity(jobKey)
//            .usingJobData(new JobDataMap(metadata))
//            .build();
//
//        TriggerKey triggerKey = TriggerKey.triggerKey(COLLECT_STOCK_LIST);
//        Trigger trigger = newTrigger()
//            .withIdentity(triggerKey)
//            .startNow()
//            .build();
//
//        runTask(job, trigger);
//    }
//
//    public void deleteTask(long id) {
//
//    }
//
//    public void runTask(JobDetail jobDetail, Trigger trigger) {
//        try {
//            scheduler.scheduleJob(jobDetail, trigger);
//        } catch (Exception e) {
//
//        }
//    }
//
//}
