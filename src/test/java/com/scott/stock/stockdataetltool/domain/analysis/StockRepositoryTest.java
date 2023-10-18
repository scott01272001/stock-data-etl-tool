package com.scott.stock.stockdataetltool.domain.analysis;

import com.scott.stock.stockdataetltool.StockDataEtlToolApplicationTests;
import com.scott.stock.stockdataetltool.domain.analysis.entity.Stock;
import com.scott.stock.stockdataetltool.infrastructure.databse.repository.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StockRepositoryTest extends StockDataEtlToolApplicationTests {

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void before() {
        stockRepository.deleteAll();
    }

    @Test
    public void findByStockIdTest() {
        Stock stock = Stock.builder().stockId("2330").stockName("台積電").type("tpex")
            .industryCategory("半導體業").build();
        stock = stockRepository.save(stock);
        Stock tsmc = stockRepository.findByStockId("2330").get();
        Assertions.assertEquals(stock.getId(), tsmc.getId());
    }

}
