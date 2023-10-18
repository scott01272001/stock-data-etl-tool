package com.scott.stock.stockdataetltool.infrastructure.databse.repository;

import com.scott.stock.stockdataetltool.domain.analysis.entity.IndustryCategory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryCategoryRepository extends JpaRepository<IndustryCategory, Long>,
    QuerydslPredicateExecutor<IndustryCategory> {

    Optional<IndustryCategory> findByType(String type);
}
