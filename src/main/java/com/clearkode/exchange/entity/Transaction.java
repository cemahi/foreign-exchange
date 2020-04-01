package com.clearkode.exchange.entity;

import com.clearkode.exchange.entity.common.DomainEntity;
import com.clearkode.exchange.entity.common.OperationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Getter
@Setter
@Entity
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Transaction extends DomainEntity {

    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;

    @Size(max = 5)
    @Column(length = 5)
    private String sourceCurrency;

    @Size(max = 5)
    @Column(length = 5)
    private String targetCurrency;

    @Enumerated(EnumType.STRING)
    @Setter
    private OperationType operation;

    private String description;

    public static Transaction Create(BigDecimal sourceAmount, BigDecimal targetAmount, Currency sourceCurrency,
                                     Currency targetCurrency, OperationType operation) {
        Transaction transaction = new Transaction();
        transaction.sourceAmount = sourceAmount;
        transaction.targetAmount = targetAmount;
        transaction.sourceCurrency = sourceCurrency.toString();
        transaction.targetCurrency = targetCurrency.toString();
        transaction.operation = operation;
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());
        return transaction;
    }
}
