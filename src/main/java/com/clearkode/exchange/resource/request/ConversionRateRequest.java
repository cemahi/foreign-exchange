package com.clearkode.exchange.resource.request;

import com.clearkode.exchange.handler.common.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionRateRequest extends BaseRequest {

    @NotNull
    private Currency source;

    @NotNull
    private Currency target;
}
