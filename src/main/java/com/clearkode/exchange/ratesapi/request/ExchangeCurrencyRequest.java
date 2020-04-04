package com.clearkode.exchange.ratesapi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeCurrencyRequest {

    @NotNull
    private Currency source;

    @NotNull
    private Currency target;

    public static ExchangeCurrencyRequest create(Currency source, Currency target){
        ExchangeCurrencyRequest request = new ExchangeCurrencyRequest();
        request.setSource(source);
        request.setTarget(target);

        return request;
    }
}
