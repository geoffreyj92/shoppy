package com.jonescorp.shoppy.service;

import com.jonescorp.shoppy.model.Catalog;
import com.jonescorp.shoppy.model.Transaction;
import com.jonescorp.shoppy.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final CartService cartService;

    private ArrayList<Integer> cart;

    Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository repository, CartService cartService) {
        this.repository = repository;
        this.cartService = cartService;
    }

    public Transaction completeTransaction(ArrayList<Integer> items, Long userId, Catalog catalogItem, int quantity) {
        //I need to find a way to extract the userId from the logged-in user and add to transaction
        Transaction transaction = new Transaction();
        boolean addClick = false;
        boolean removeClick = false;

        if (addClick) {
            cartService.addToCart(catalogItem, quantity);
        }

        if (removeClick) {
            cartService.removeFromCart(catalogItem, quantity);
        }


        //NEED TO FINISH PROCESSING OF TRANSACTION







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

    public List<Transaction> getAllTransactions(String userId) {
        return repository.findAll();
    }

    public List<Transaction> getAllTransactionsByDate(Date date) {
        return repository.findAllByDate(date);
    }

    public List<Transaction> getAllTransactionsByUserId(Long userId) {
        return repository.findAllById(Collections.singleton(userId));
    }
}
