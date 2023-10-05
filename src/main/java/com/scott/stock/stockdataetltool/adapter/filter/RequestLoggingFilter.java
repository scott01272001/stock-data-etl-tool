package com.scott.stock.stockdataetltool.adapter.filter;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Log4j2
public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

    public RequestLoggingFilter() {
        setIncludeQueryString(true);
        setIncludeHeaders(true);
        setIncludePayload(true);
        setMaxPayloadLength(1024);
        setAfterMessagePrefix("");
        setAfterMessageSuffix("");
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        log.debug("[{}] {}", request.getMethod(),
            URLDecoder.decode(message, StandardCharsets.UTF_8));
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
    }

}
