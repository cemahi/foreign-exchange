package com.clearkode.exchange.ratesapi.request;

import com.clearkode.exchange.handler.common.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListConversionRequest extends BaseRequest {

    private UUID transactionId;
    private LocalDateTime transactionDate;
    private Pageable pageable;

    public static ListConversionRequest create(UUID transactionId, LocalDateTime transactionDate, Pageable pageable){
        ListConversionRequest request = new ListConversionRequest();
        request.setTransactionId(transactionId);
        request.setTransactionDate(transactionDate);
        request.setPageable(pageable);
        return request;
    }
}
