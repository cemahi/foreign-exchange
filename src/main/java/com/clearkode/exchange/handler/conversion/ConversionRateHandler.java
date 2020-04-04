package com.clearkode.exchange.handler.conversion;

import com.clearkode.exchange.handler.common.BaseHandler;
import com.clearkode.exchange.handler.common.Handler;
import com.clearkode.exchange.ratesapi.request.ExchangeCurrencyRequest;
import com.clearkode.exchange.ratesapi.response.ExchangeCurrencyResponse;
import com.clearkode.exchange.resource.request.ConversionRateRequest;
import com.clearkode.exchange.resource.response.ConversionRateResponse;
import com.clearkode.exchange.service.RatesApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
@Transactional
public class ConversionRateHandler extends BaseHandler implements Handler<ConversionRateRequest, ConversionRateResponse> {

    private final RatesApiService ratesApiService;

    @Override
    @Transactional(readOnly = true)
    public ConversionRateResponse execute(ConversionRateRequest request) {
        ConversionRateResponse response = new ConversionRateResponse();
        ExchangeCurrencyResponse exchangeResponse = ratesApiService.getConversionRate(ExchangeCurrencyRequest.create(request.getSource(), request.getTarget()));
        response.setRate(new BigDecimal(exchangeResponse.getRates().entrySet().iterator().next().getValue()));
        return response;
    }
}
