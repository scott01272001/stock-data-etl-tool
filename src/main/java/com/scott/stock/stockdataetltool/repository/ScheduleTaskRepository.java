package com.scott.stock.stockdataetltool.repository;

import com.scott.stock.stockdataetltool.model.ScheduleTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTaskRepository extends JpaRepository<ScheduleTask, Long>,
    JpaSpecificationExecutor<ScheduleTask>, PagingAndSortingRepository<ScheduleTask, Long> {

}
