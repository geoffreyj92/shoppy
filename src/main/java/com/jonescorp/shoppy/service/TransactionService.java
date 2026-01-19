package com.jonescorp.shoppy.service;

import com.jonescorp.shoppy.model.Catalog;
import com.jonescorp.shoppy.model.Transaction;
import com.jonescorp.shoppy.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {

    private final TransactionRepository repository;

    private ArrayList<Integer> cart;

    Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction completeTransaction(ArrayList<Integer> items, Long userId) {
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

    private void addToCart() {

    }

    public List<Transaction> getAllTransactions(String userId) {
        return repository.findAll();
    }

    //TODO: NEED to update this method to get transactions for username or userid
//    public Optional<Catalog> getCatalogByUsernameOrUserId(Long id, String username) {
//        Optional<Catalog> catalogById = catalogRepository.findById(id);
//        Optional<Catalog> catalogByUsername = catalogRepository.findByUsername(username);
//
//        if (catalogByUsername.isPresent()) {
//            return catalogByUsername;
//        } else if (catalogById.isPresent()) {
//            return catalogById;
//        } else {
//            throw new RuntimeException("Catalog not found");
//        }
//    }


}
