package com.scott.stock.stockdataetltool.repository;

import com.scott.stock.stockdataetltool.model.Account;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    public Optional<Account> findByEmail(String email);

}
