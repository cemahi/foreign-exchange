package com.clearkode.exchange.ratesapi.response;

import com.clearkode.exchange.entity.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionResponse {
    private UUID transactionId;
    private BigDecimal targetAmount;
    private BigDecimal sourceAmount;
    private String sourceCurrency;
    private String targetCurrency;
    private LocalDateTime transactionDate;

    public static ConversionResponse create(Transaction transaction){
        ConversionResponse response = new ConversionResponse();
        response.setTransactionId(transaction.getId());
        response.setTargetAmount(transaction.getTargetAmount());
        response.setSourceAmount(transaction.getSourceAmount());
        response.setSourceCurrency(transaction.getSourceCurrency());
        response.setTargetCurrency(transaction.getTargetCurrency());
        response.setTransactionDate(transaction.getCreatedAt());
        return response;
    }
}
