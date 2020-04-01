package com.clearkode.exchange.ratesapi.response;

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
    private Currency sourceCurrency;
    private BigDecimal targetCurrency;
    private LocalDateTime transactionDate;
}
