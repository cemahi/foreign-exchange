package com.clearkode.exchange.resource.request;

import com.clearkode.exchange.handler.common.BaseRequest;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MakeConversionRequest extends BaseRequest {
    @NotNull
    private BigDecimal sourceAmount;

    @NotNull
    private Currency sourceCurrency;

    @NotNull
    private Currency targetCurrency;
}
