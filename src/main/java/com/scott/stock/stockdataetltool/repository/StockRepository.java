package com.scott.stock.stockdataetltool.repository;

import com.scott.stock.stockdataetltool.model.ScheduleTask;
import com.scott.stock.stockdataetltool.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, String>,
    JpaSpecificationExecutor<Stock>, PagingAndSortingRepository<Stock, String> {


}
