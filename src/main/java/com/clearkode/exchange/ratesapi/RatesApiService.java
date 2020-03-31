package com.clearkode.exchange.ratesapi;

import com.clearkode.exchange.ratesapi.request.ExchangeCurrencyRequest;
import com.clearkode.exchange.ratesapi.response.ExchangeCurrencyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Currency;

@Slf4j
@RequiredArgsConstructor
public class RatesApiService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final String latestRatePath = "/latest?";

    public ExchangeCurrencyResponse getConversionRate(ExchangeCurrencyRequest request) throws RatesApiException {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(latestRatePath + "base=" + request.getSource().toString() + "&symbols=" + request.getTarget().toString());
        ExchangeCurrencyResponse response = restTemplate.getForObject(uriComponentsBuilder.build().toString(), ExchangeCurrencyResponse.class);

        return response;
    }

}
