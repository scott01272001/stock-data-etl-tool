package com.scott.stock.stockdataetltool.infrastructure.databse.repository;

import com.scott.stock.stockdataetltool.domain.collectdata.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
