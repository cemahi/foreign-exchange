package com.clearkode.exchange.resource.response;

import com.clearkode.exchange.handler.common.BaseResponse;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConversionRateResponse extends BaseResponse {
     private BigDecimal rate;
}
