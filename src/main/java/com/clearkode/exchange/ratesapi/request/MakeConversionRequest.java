package com.clearkode.exchange.ratesapi.request;

import com.clearkode.exchange.handler.common.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeConversionRequest extends BaseRequest {
    @NotNull
    private BigDecimal sourceAmount;

    @NotNull
    private Currency sourceCurrency;

    @NotNull
    private Currency targetCurrency;
}
