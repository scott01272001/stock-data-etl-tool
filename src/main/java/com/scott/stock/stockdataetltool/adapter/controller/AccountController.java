//package com.scott.stock.stockdataetltool.adapter.controller;
//
//import com.scott.stock.stockdataetltool.application.dto.TokenRequest;
//import com.scott.stock.stockdataetltool.application.exception.DuplicatedObjectException;
//import com.scott.stock.stockdataetltool.infrastructure.databse.repository.AccountRepository;
//import com.scott.stock.stockdataetltool.model.Account;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import java.util.List;
//import java.util.Optional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//@Tag(name = "Account")
//@RestController
//@RequestMapping(value = "/api/accounts")
//@Log4j2
//@RequiredArgsConstructor
//public class AccountController {
//
//    private final AccountRepository accountRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Operation(summary = "Create user", responses = {
//        @ApiResponse(responseCode = "201", description = "Create", content = @Content(mediaType = "application/json")),
//        @ApiResponse(responseCode = "400", description = "Invalid username", content = @Content(mediaType = "application/json"))})
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Account create(@RequestBody @Validated TokenRequest tokenRequest)
//        throws DuplicatedObjectException {
//        Optional<Account> account = accountRepository.findByEmail(tokenRequest.getUsername());
//        if (account.isPresent()) {
//            throw new DuplicatedObjectException("username");
//        }
//
//        String encryptPassword = passwordEncoder.encode(tokenRequest.getPassword());
//        Account newAccount = new Account(tokenRequest.getUsername(), encryptPassword);
//        return accountRepository.save(newAccount);
//    }
//
//    @Operation(summary = "Get users", responses = {
//        @ApiResponse(responseCode = "200", description = "Get all user", content = @Content(mediaType = "application/json"))})
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Account> findAccounts() {
//        return accountRepository.findAll();
//    }
//
//}
