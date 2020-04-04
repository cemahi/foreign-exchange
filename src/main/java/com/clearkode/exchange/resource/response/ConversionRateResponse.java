package com.clearkode.exchange.resource.response;

import com.clearkode.exchange.handler.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionRateResponse extends BaseResponse {
     private BigDecimal rate;
}
