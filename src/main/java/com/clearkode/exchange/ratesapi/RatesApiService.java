package com.clearkode.exchange.ratesapi;

import com.clearkode.exchange.ratesapi.response.RateResponse;
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

    private final String latestRatePath = "/latest";

    public RateResponse getConversionRate() throws RatesApiException {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(latestRatePath);
        return restTemplate.getForObject(uriComponentsBuilder.build().toString(), RateResponse.class);
    }

}
