package com.clearkode.exchange.entity.transaction;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionSpec {

    public static Specification<Transaction> transactionId(UUID transactionId) {
        return (root, query, cb) -> cb.equal(root.get(Transaction_.ID), transactionId);
    }

    public static Specification<Transaction> start(LocalDateTime start) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Transaction_.CREATED_AT), start);
    }

    public static Specification<Transaction> end(LocalDateTime finish) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Transaction_.CREATED_AT), finish);
    }
}
