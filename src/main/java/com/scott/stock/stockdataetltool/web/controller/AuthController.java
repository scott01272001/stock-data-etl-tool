package com.scott.stock.stockdataetltool.web.controller;

import com.scott.stock.stockdataetltool.model.Account;
import com.scott.stock.stockdataetltool.repository.AccountRepository;
import com.scott.stock.stockdataetltool.service.JwtTokenService;
import com.scott.stock.stockdataetltool.web.dto.TokenRequest;
import com.scott.stock.stockdataetltool.web.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/token")
public class AuthController {

  private final JwtTokenService jwtTokenService;
  private final AccountRepository accountRepository;
  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;

  @PostMapping
  public TokenResponse getToken(@RequestBody TokenRequest tokenRequest) {
    Account account = accountRepository.findByEmail(tokenRequest.getUsername()) //
        .orElseThrow(() -> new UsernameNotFoundException(String.format("Account '%s' not exist", tokenRequest.getUsername())));

    if (!passwordEncoder.matches(tokenRequest.getPassword(), account.getPassword())) {
      throw new BadCredentialsException(String.format("Invalid password."));
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(tokenRequest.getUsername());
    String token = jwtTokenService.generateToken(userDetails);

    TokenResponse tokenResponse = TokenResponse.builder().accessToken(token).build();
    return tokenResponse;
  }

}
