package com.example.trade.repository;

import com.example.trade.model.Transaction;
import com.example.trade.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTransactionType(TransactionType transactionType);
}
