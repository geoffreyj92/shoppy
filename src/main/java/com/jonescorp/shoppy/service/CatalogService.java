package com.jonescorp.shoppy.service;

import com.jonescorp.shoppy.model.Catalog;
import com.jonescorp.shoppy.repository.CatalogRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;
    Logger log = LoggerFactory.getLogger(CatalogService.class);

    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public ArrayList<Catalog> getAll() {
        return (ArrayList<Catalog>) catalogRepository.findAll();
    }

    public Optional<Catalog> getAllCatalogByBusinessId(Long businessId) {
        Optional<Catalog> catalogs = catalogRepository.findById(businessId);

        if (catalogRepository.findById(businessId).isPresent()) {
            return catalogs;
        } else {
            throw new RuntimeException("Catalog not found");
        }
    }

    public Optional<Catalog> getCatalogItemsByDate(Date date) {
        return catalogRepository.getAllByDate(date);
    }

    public Catalog createCatalogItem(Long catalogId, String name, String description, String category, Double price, Integer sku, Integer quantity) {
        Catalog catalog = new Catalog();

        Optional<Catalog> existingCatalogItem = catalogRepository.findById(catalogId);
        if (existingCatalogItem.isPresent()) {
            throw new RuntimeException("Catalog item already exists");
        } else {
            catalog.setName(name);
            catalog.setDescription(description);
            catalog.setCategory(category);
            catalog.setPrice(price);
            catalog.setSku(sku);
            catalog.setQuantity(quantity + updateQuantityFromDbAmount(catalogId));
            // business id needs to pulled from business of logged in owner
            catalog.setBusinessId(getIdOfLoggedInBusinessOwner());
        }


        try {
            catalogRepository.save(catalog);
            log.info("Catalog item created");
        } catch (RuntimeException e) {
            new RuntimeException("Catalog item already exists");
            log.error("Error while saving catalog item");
        }

        return catalog;
    }

    public Catalog updateCatalogItem(Long catalogId, String name, String description, String category, Double price, Integer quantity) {
        //TODO: Need to find a way to not allow values to change in DB
        // items to not change 1. sku
        // 2. businessId

        Optional<Catalog> existingCatalogItem = catalogRepository.findById(catalogId);
        if (existingCatalogItem.isEmpty()) {
            throw new RuntimeException("Catalog item does not exist");
        }
        Catalog catalog = existingCatalogItem.get();
        catalog.setName(name);
        catalog.setDescription(description);
        catalog.setCategory(category);
        catalog.setPrice(price);
        catalog.setQuantity(quantity + updateQuantityFromDbAmount(catalogId));
        catalog.setBusinessId(getIdOfLoggedInBusinessOwner());
        try {
            catalogRepository.save(catalog);
            log.info("Catalog item updated");
        } catch (RuntimeException e) {
            new RuntimeException("Error in updating catalog item with ID: " + catalogId);
        }
        return catalog;
    }

    private Long getIdOfLoggedInBusinessOwner() {
        //TODO: Update with logic to capture id of logged-in business owner
        return 12134L;
    }

    private Integer updateQuantityFromDbAmount(Long catalogId) {
        Optional<Catalog> catalogItem = catalogRepository.findById(catalogId);

        if (catalogItem.isEmpty()) {
            return 0;
        }
           return catalogItem.get().getQuantity();
    }
}
