package com.clearkode.exchange.resource;

import com.clearkode.exchange.ratesapi.RatesApiService;
import com.clearkode.exchange.ratesapi.request.ExchangeCurrencyRequest;
import com.clearkode.exchange.ratesapi.response.ExchangeCurrencyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class ExchangeController {

    private final RatesApiService ratesApiService;

    @PostMapping("/rate")
    public ExchangeCurrencyResponse getConversionRate(@Valid @RequestBody ExchangeCurrencyRequest request) {
        return ratesApiService.getConversionRate(request);
    }

    @PostMapping("/conversion")
    public ExchangeCurrencyResponse makeConversion(@Valid @RequestBody ExchangeCurrencyRequest request) {
        return ratesApiService.getConversionRate(request);
    }

    @GetMapping("/conversion")
    public ExchangeCurrencyResponse getConversions(@Valid @RequestBody ExchangeCurrencyRequest request) {
        return ratesApiService.getConversionRate(request);
    }
}