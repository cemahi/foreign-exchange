package com.clearkode.exchange.resource.response;

import com.clearkode.exchange.handler.common.BaseResponse;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MakeConversionResponse extends BaseResponse {
    private UUID transactionId;
    private BigDecimal targetAmount;
}
