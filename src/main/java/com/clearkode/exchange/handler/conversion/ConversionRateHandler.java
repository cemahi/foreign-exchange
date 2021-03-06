package com.clearkode.exchange.handler.conversion;

import com.clearkode.exchange.config.ProviderFactory;
import com.clearkode.exchange.config.ProviderType;
import com.clearkode.exchange.handler.common.BaseHandler;
import com.clearkode.exchange.handler.common.Handler;
import com.clearkode.exchange.ratesapi.request.ExchangeCurrencyRequest;
import com.clearkode.exchange.resource.request.ConversionRateRequest;
import com.clearkode.exchange.resource.response.ConversionRateResponse;
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

    private final ProviderFactory providerFactory;

    @Override
    @Transactional(readOnly = true)
    public ConversionRateResponse execute(ConversionRateRequest request) {
        ConversionRateResponse response = new ConversionRateResponse();
        BigDecimal rate = providerFactory.getProvider(ProviderType.RATESAPI).getConversionRate(ExchangeCurrencyRequest
                .create(request.getSource(), request.getTarget()));
        response.setRate(rate);
        return response;
    }
}
