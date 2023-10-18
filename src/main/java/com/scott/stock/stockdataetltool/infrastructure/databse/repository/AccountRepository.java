package com.scott.stock.stockdataetltool.infrastructure.databse.repository;

import com.scott.stock.stockdataetltool.domain.usermanagement.entity.Account;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>,
    QuerydslPredicateExecutor<Account> {

    public Optional<Account> findByEmail(String email);

}