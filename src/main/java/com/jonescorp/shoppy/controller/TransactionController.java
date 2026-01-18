package com.jonescorp.shoppy.controller;

import com.jonescorp.shoppy.model.Transaction;
import com.jonescorp.shoppy.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    public Optional<List<Transaction>> getAllTransactions() {
        return Optional.empty();
    }

    @PostMapping("/username/transactions")
    public Transaction createTransaction(@RequestBody
                                             @RequestParam ArrayList<Integer> items,
                                         @RequestParam Integer userId) {
        //will need to take the items from the cart and post a transaction for the items in cart
        //will need to clear cart when items submitted and store transaction in DB
        return transactionService.createTransaction(items, userId);
    }

    public Optional<Transaction> getTransactionById(@RequestParam Integer transactionId) {
        return Optional.empty();
    }

    public Optional<Transaction> getTransactionsByUsername(@RequestParam String Username) {
        return Optional.empty();
    }

    public Optional<Transaction> updateTransactionId(@RequestBody Integer transactionId, @RequestParam Integer transaction) {
        //updates to transactions will be for returns and cancelled orders
        return Optional.empty();
    }

    public Optional<Transaction> deleteTransactionById(@RequestParam Integer transactionId) {
        return Optional.empty();
    }
}
