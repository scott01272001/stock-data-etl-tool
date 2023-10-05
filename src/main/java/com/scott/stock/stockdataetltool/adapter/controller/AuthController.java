//package com.scott.stock.stockdataetltool.adapter.controller;
//
//import com.scott.stock.stockdataetltool.application.dto.TokenRequest;
//import com.scott.stock.stockdataetltool.application.dto.TokenResponse;
//import com.scott.stock.stockdataetltool.application.service.JwtTokenService;
//import com.scott.stock.stockdataetltool.domain.usermanagement.entity.Account;
//import com.scott.stock.stockdataetltool.infrastructure.databse.repository.AccountRepository;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//@Tag(name = "Auth")
//@RequiredArgsConstructor
//@RestController
//@RequestMapping(value = "/api/token")
//public class AuthController {
//
//    private final JwtTokenService jwtTokenService;
//    private final AccountRepository accountRepository;
//    private final UserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Operation(summary = "Get JWT access token", responses = {
//        @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json")),
//        @ApiResponse(responseCode = "401", description = "Invalid user or password", content = @Content(mediaType = "application/json"))})
//    @PostMapping
//    @ResponseStatus(HttpStatus.OK)
//    public TokenResponse getToken(@RequestBody TokenRequest tokenRequest) {
//        Account account = accountRepository.findByEmail(tokenRequest.getUsername()) //
//            .orElseThrow(() -> new UsernameNotFoundException(
//                String.format("Account '%s' not exist", tokenRequest.getUsername())));
//
//        if (!passwordEncoder.matches(tokenRequest.getPassword(), account.getPassword())) {
//            throw new BadCredentialsException("Invalid password.");
//        }
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(tokenRequest.getUsername());
//        String token = jwtTokenService.generateToken(userDetails);
//
//        return TokenResponse.builder().accessToken(token).build();
//    }
//
//}
