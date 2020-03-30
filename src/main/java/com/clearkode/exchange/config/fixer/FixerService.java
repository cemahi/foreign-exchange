package com.clearkode.exchange.config.fixer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
public class FixerService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
}
