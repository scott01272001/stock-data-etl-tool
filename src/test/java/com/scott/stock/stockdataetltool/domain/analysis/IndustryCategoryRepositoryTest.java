package com.scott.stock.stockdataetltool.domain.analysis;

import com.scott.stock.stockdataetltool.StockDataEtlToolApplicationTests;
import com.scott.stock.stockdataetltool.domain.analysis.entity.IndustryCategory;
import com.scott.stock.stockdataetltool.infrastructure.databse.repository.IndustryCategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IndustryCategoryRepositoryTest extends StockDataEtlToolApplicationTests {

    @Autowired
    private IndustryCategoryRepository industryCategoryRepository;

    @BeforeEach
    public void before() {
        industryCategoryRepository.deleteAll();
    }

    @Test
    public void findByTypeTest() {
        IndustryCategory industryCategory = IndustryCategory.builder().type("半導體業").build();
        industryCategoryRepository.save(industryCategory);
        IndustryCategory i = industryCategoryRepository.findByType("半導體業").get();
        Assertions.assertEquals(industryCategory, i);
    }
}
