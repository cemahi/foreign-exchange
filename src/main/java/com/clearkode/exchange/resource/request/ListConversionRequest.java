package com.clearkode.exchange.resource.request;

import com.clearkode.exchange.handler.common.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListConversionRequest extends BaseRequest {

    private UUID transactionId;
    private LocalDateTime startTransactionDate;
    private LocalDateTime endTransactionDate;
    private Pageable pageable;

    public static ListConversionRequest create(UUID transactionId, LocalDateTime startTransactionDate,
                                               LocalDateTime endTransactionDate, Pageable pageable){
        ListConversionRequest request = new ListConversionRequest();
        request.setTransactionId(transactionId);
        request.setStartTransactionDate(startTransactionDate);
        request.setEndTransactionDate(endTransactionDate);
        request.setPageable(pageable);
        return request;
    }
}
