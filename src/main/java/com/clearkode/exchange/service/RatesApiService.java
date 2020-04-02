package com.clearkode.exchange.service;

import com.clearkode.exchange.ratesapi.config.RatesApiException;
import com.clearkode.exchange.ratesapi.request.ExchangeCurrencyRequest;
import com.clearkode.exchange.ratesapi.response.ExchangeCurrencyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
public class RatesApiService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final String latestRatePath = "/latest?";

    public ExchangeCurrencyResponse getConversionRate(ExchangeCurrencyRequest request) throws RatesApiException {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(latestRatePath + "base=" + request.getSource().toString() + "&symbols=" + request.getTarget().toString());

        return restTemplate.getForObject(uriComponentsBuilder.build().toString(), ExchangeCurrencyResponse.class);
    }
}
