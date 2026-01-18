package com.jonescorp.shoppy.repository;

import com.jonescorp.shoppy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
