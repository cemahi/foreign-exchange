package com.clearkode.exchange.service;

import com.clearkode.exchange.config.IApiService;
import com.clearkode.exchange.entity.common.CommonAppException;
import com.clearkode.exchange.entity.common.ErrorType;
import com.clearkode.exchange.ratesapi.config.RatesApiException;
import com.clearkode.exchange.ratesapi.request.ExchangeCurrencyRequest;
import com.clearkode.exchange.ratesapi.response.ExchangeCurrencyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
public class RatesApiService implements IApiService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public BigDecimal getConversionRate(ExchangeCurrencyRequest request) throws RatesApiException {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/latest?base=" +
                request.getSource().toString() + "&symbols=" + request.getTarget().toString());

        ExchangeCurrencyResponse response = restTemplate.getForObject(uriComponentsBuilder.build().toString(), ExchangeCurrencyResponse.class);
        if (response == null || response.getRates() == null ){
            throw new CommonAppException(ErrorType.RATE_NOT_FOUND);
        }
        return new BigDecimal(response.getRates().entrySet().iterator().next().getValue());
    }
}
