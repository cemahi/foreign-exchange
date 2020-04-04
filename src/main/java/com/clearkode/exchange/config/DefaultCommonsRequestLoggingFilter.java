package com.clearkode.exchange.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DefaultCommonsRequestLoggingFilter extends OncePerRequestFilter {

    private static void logRequestHeader(ContentCachingRequestWrapper request, String prefix) {
        String queryString = request.getQueryString();
        if (queryString == null) {
            log.info("{} {} {}", prefix, request.getMethod(), request.getRequestURI());
        } else {
            log.info("{} {} {}?{}", prefix, request.getMethod(), request.getRequestURI(), queryString);
        }
        Map<String, Object> headers = new HashMap<>();

        Collections.list(request.getHeaderNames()).forEach(headerName -> {
            headers.put(headerName, request.getHeader(headerName));
        });

        try {
            log.info("{} {}: {}", prefix, "headers", (new ObjectMapper()).writeValueAsString(headers));
        } catch (JsonProcessingException e) {
            log.error("Request headers are not parsed. headers: {}", headers);
            log.error(e.getMessage(), e);
        }
    }

    private static void logRequestBody(ContentCachingRequestWrapper request, String prefix) {
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, request.getCharacterEncoding(), prefix);
        }
    }

    private static void logResponse(ContentCachingResponseWrapper response, String prefix) {
        int status = response.getStatus();
        log.info("{} {} {}", prefix, status, HttpStatus.valueOf(status).getReasonPhrase());

        Map<String, Object> headers = new HashMap<>();

        response.getHeaderNames().forEach(headerName -> {
            headers.put(headerName, response.getHeader(headerName));
        });

        try {
            log.info("{} {}: {}", prefix, "headers", (new ObjectMapper()).writeValueAsString(headers));
        } catch (JsonProcessingException e) {
            log.error("Response headers are not parsed. headers: {}", headers);
            log.error(e.getMessage(), e);
        }

        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, response.getCharacterEncoding(), prefix);
        }
    }

    private static void logContent(byte[] content, String contentEncoding, String prefix) {
        try {
            String contentString = new String(content, contentEncoding);
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference typeReference = new TypeReference<Map<String, Object>>() {
            };
            Map<String, Object> jsonData = (Map<String, Object>) objectMapper.readValue(contentString, typeReference);
            log.info("{} body : {}", prefix, objectMapper.writeValueAsString(jsonData));
        } catch (IOException e) {
            log.info("{} [{} bytes content]", prefix, content.length);
        }
    }

    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        } else {
            return new ContentCachingRequestWrapper(request);
        }
    }

    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
        }
    }

    protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        try {
            beforeRequest(request, response);
            filterChain.doFilter(request, response);
        } finally {
            afterRequest(request, response);
            response.copyBodyToResponse();
        }
    }

    protected void beforeRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
        if (log.isInfoEnabled()) {
            logRequestHeader(request, "Request");
        }
    }

    protected void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
        if (log.isInfoEnabled()) {
            logRequestBody(request, "Request");
            logResponse(response, "Response");
        }
    }
}
