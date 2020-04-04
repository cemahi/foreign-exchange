package com.clearkode.exchange.config;

import com.clearkode.exchange.ratesapi.request.ExchangeCurrencyRequest;

import java.math.BigDecimal;

public interface IApiService {
    BigDecimal getConversionRate(ExchangeCurrencyRequest request);
}
