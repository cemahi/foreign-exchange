package com.clearkode.exchange.resource.response;

import com.clearkode.exchange.handler.common.BaseResponse;;
import lombok.*;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListConversionResponse extends BaseResponse {

    private Page<ConversionResponse> conversions;
}
