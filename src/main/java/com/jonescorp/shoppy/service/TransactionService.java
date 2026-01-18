package com.jonescorp.shoppy.service;

import com.jonescorp.shoppy.model.Transaction;
import com.jonescorp.shoppy.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TransactionService {

    private final TransactionRepository repository;
    Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction createTransaction(ArrayList<Integer> items, Integer userId) {
        //I need to find a way to extract the userId from the logged-in user and add to transaction
        Transaction transaction = new Transaction();

        if (!items.isEmpty()) {
            transaction.setItems(items);
        }
        transaction.setUserId(userId);

        try {
            repository.save(transaction);
            logger.info("Sale Completed!");
        }
        catch (Exception e) {
            e.getMessage();
            logger.info("Sale Failed with items: {}", transaction);
        }
       return transaction;
    }

    public void addToCart() {

    }

    public List<Transaction> getAllTransactions(String userId) {
        return repository.findAll();
    }


}
