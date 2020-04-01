package com.clearkode.exchange.ratesapi.response;

import com.clearkode.exchange.handler.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeConversionResponse extends BaseResponse {
    private UUID transactionId;
    private BigDecimal targetAmount;
}
