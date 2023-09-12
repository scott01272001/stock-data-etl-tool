package com.scott.stock.stockdataetltool.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scott.stock.stockdataetltool.model.Account;
import com.scott.stock.stockdataetltool.repository.AccountRepository;
import com.scott.stock.stockdataetltool.service.JwtTokenService;
import com.scott.stock.stockdataetltool.web.filter.JwtAuthenticationFilter;
import com.scott.stock.stockdataetltool.web.filter.RequestLoggingFilter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] PREMITS_ENDPOINT = {
        "/api/token",
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-ui.html"
    };
    private final AccountRepository accountRepository;
    private final JwtTokenService jwtTokenService;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());

        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(request -> request //
            .requestMatchers(PREMITS_ENDPOINT) //
            .permitAll()
            .anyRequest().authenticated());

        http.addFilterBefore(
            new JwtAuthenticationFilter(jwtTokenService, userDetailsService(), objectMapper),
            UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(new RequestLoggingFilter(), JwtAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        ConcurrentMapCache concurrentMapCache = new ConcurrentMapCache("user-cache");
        SpringCacheBasedUserCache springCacheBasedUserCache = new SpringCacheBasedUserCache(
            concurrentMapCache);

        UserDetailsService userDetailsService = username -> {
            Account account = accountRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                    String.format("Account '%s' not exist", username)));

            // TODO: handle role
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("admin"));

            UserDetails userDetails = new User(account.getEmail(), account.getPassword(),
                grantedAuthorities);
            return userDetails;
        };

        CachingUserDetailsService cachingUserDetailsService = new CachingUserDetailsService(
            userDetailsService);
        cachingUserDetailsService.setUserCache(springCacheBasedUserCache);
        return cachingUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
