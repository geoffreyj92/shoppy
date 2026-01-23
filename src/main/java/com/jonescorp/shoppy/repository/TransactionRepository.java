package com.jonescorp.shoppy.repository;

import com.jonescorp.shoppy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByDate(Date date);
}
