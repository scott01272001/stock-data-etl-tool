package com.scott.stock.stockdataetltool.web.controller;

import com.scott.stock.stockdataetltool.exception.DuplicatedObjectException;
import com.scott.stock.stockdataetltool.model.Account;
import com.scott.stock.stockdataetltool.repository.AccountRepository;
import com.scott.stock.stockdataetltool.web.dto.TokenRequest;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/account")
@Log4j2
@RequiredArgsConstructor
public class AccountController {

  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Account create(@RequestBody @Validated TokenRequest tokenRequest) throws DuplicatedObjectException {
    Optional<Account> account = accountRepository.findByEmail(tokenRequest.getUsername());
    if (account.isPresent()) {
      throw new DuplicatedObjectException("username");
    }

    String encryptPassword = passwordEncoder.encode(tokenRequest.getPassword());
    Account newAccount = new Account(tokenRequest.getUsername(), encryptPassword);
    return accountRepository.save(newAccount);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Account> findAccounts() {
    return accountRepository.findAll();
  }

}
