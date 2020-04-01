package com.clearkode.exchange.ratesapi.response;

import com.clearkode.exchange.handler.common.BaseResponse;;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListConversionResponse extends BaseResponse {

    private Page<ConversionResponse> conversions;
}
