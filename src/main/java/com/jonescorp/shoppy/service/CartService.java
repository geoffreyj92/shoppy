package com.jonescorp.shoppy.service;


import com.jonescorp.shoppy.model.Catalog;
import com.jonescorp.shoppy.repository.CatalogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CartService {

    //TODO: 1. create method to start a new order which creates a new cart
    // 2. query catalog for items
    // 3. add method to add items to cart and remove items to remove from cart
    // 4. something like while shopping is true, add and remove methods active while cart is in process
    // 5. will send cart to transaction service to be processed at checkout

    private final CatalogService catalogService;
    private final CatalogRepository catalogRepository;

//    private List<Catalog> cart = new ArrayList();
    private Map<Catalog, Integer> cart = new HashMap<>();

    public CartService(CatalogService catalogService, CatalogRepository catalogRepository) {
        this.catalogService = catalogService;
        this.catalogRepository = catalogRepository;
    }

    public void addToCart(Catalog catalogItem, int quantity) {
        cart.merge(catalogItem, quantity, Integer::sum);
    }

    public void removeFromCart(Catalog catalogItem, int quantity) {
        cart.computeIfPresent(catalogItem, (k,v) -> {
            int newQuantity = v - quantity;
            return newQuantity > 0 ? newQuantity : null;
        });
    }

    public double getTotalPrice() {
        return cart.entrySet().stream().mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue()).sum();
    }
}
