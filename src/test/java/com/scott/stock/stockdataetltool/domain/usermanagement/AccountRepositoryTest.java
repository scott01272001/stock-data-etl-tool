package com.scott.stock.stockdataetltool.domain.usermanagement;

import com.scott.stock.stockdataetltool.StockDataEtlToolApplicationTests;
import com.scott.stock.stockdataetltool.domain.usermanagement.entity.Account;
import com.scott.stock.stockdataetltool.infrastructure.databse.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountRepositoryTest extends StockDataEtlToolApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void before() {
        accountRepository.deleteAll();
    }

    @Test
    public void createTest() {
        Account account = Account.builder().email("test").password("test").build();
        account = accountRepository.save(account);

        account = accountRepository.findById(account.getId()).get();

        System.out.println(account);
    }
}
