package com.jonescorp.shoppy.controller;

import com.jonescorp.shoppy.model.Transaction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {




    public Optional<List<Transaction>> getAllTransactions() {
        return Optional.empty();
    }

    public Optional<Transaction> getTransactionById(@RequestParam Integer transactionId) {
        return Optional.empty();
    }

    public Optional<Transaction> getTransactionsByUsername(@RequestParam String Username) {
        return Optional.empty();
    }

    public Optional<Transaction> createTransaction(@RequestBody Transaction transaction) {
        //will need to take the items from the cart and post a transaction for the items in cart
        //will need to clear cart when items submitted and store transaction in DB
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
