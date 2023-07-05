package com.scott.stock.stockdataetltool.repository;

import com.scott.stock.stockdataetltool.model.ScheduleTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTaskRepository extends JpaRepository<ScheduleTask, Long>,
    QuerydslPredicateExecutor<ScheduleTask> {

}
