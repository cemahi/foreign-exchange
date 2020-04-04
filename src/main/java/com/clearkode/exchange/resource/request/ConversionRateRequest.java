package com.clearkode.exchange.resource.request;

import com.clearkode.exchange.handler.common.BaseRequest;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Currency;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConversionRateRequest extends BaseRequest {

    @NotNull
    private Currency source;

    @NotNull
    private Currency target;
}
