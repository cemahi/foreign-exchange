package com.clearkode.exchange.repository;

import com.clearkode.exchange.entity.common.DomainRepository;
import com.clearkode.exchange.entity.transaction.Transaction;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends DomainRepository<Transaction, UUID> {
}
