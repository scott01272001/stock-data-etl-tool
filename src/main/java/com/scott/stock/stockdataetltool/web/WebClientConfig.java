package com.scott.stock.stockdataetltool.web;

import com.scott.stock.stockdataetltool.exception.ConnectionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.ConnectException;
import java.net.URI;

@Configuration
@Log4j2
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
       return WebClient.builder()
            .codecs(config -> config.defaultCodecs().maxInMemorySize(4 * 1024 * 1024))
            .filter(exchangeFilterFunction())
            .defaultStatusHandler(HttpStatusCode::isError, resp -> {
                int code = resp.statusCode().value();
                return resp.bodyToMono(String.class).map(t -> new ConnectionException(code , t));
            })
            .build();
    }

    private ExchangeFilterFunction exchangeFilterFunction() {
        return (request, next) -> {
            if (log.isDebugEnabled()) {
                log.debug("url: {}", request.url());
            }
            return next.exchange(request);
        };
    }

}
