package com.scott.stock.stockdataetltool.repository;

import com.scott.stock.stockdataetltool.model.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {

}
