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
public class ListConversionRequest extends BaseRequest {
    @NotNull
    private BigDecimal sourceAmount;

    @NotNull
    private Currency sourceCurrency;


    public static ListConversionRequest create(){
        ListConversionRequest request = new ListConversionRequest();

        return request;
    }
}
