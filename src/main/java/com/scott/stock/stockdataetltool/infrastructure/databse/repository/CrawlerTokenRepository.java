package com.scott.stock.stockdataetltool.infrastructure.databse.repository;

import com.scott.stock.stockdataetltool.domain.collectdata.entity.CrawlerToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlerTokenRepository extends JpaRepository<CrawlerToken, Long>,
    QuerydslPredicateExecutor<CrawlerToken> {

}
