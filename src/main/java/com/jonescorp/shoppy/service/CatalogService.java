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

    public ArrayList<Catalog> getCatalogs() {
        return (ArrayList<Catalog>) catalogRepository.findAll();
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

    public Optional<Catalog> getCatalogByUsernameOrUserId(Long id,  String username) {
        Optional<Catalog> catalogById = catalogRepository.findById(id);
        Optional<Catalog> catalogByUsername = catalogRepository.findByUsername(username);

        if (catalogByUsername.isPresent()) {
            return catalogByUsername;
        } else if (catalogById.isPresent()) {
            return catalogById;
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
            catalog = existingCatalogItem.get();
        } else {
            catalog.setName(name);
            catalog.setDescription(description);
            catalog.setCategory(category);
            catalog.setPrice(price);
            catalog.setSku(sku);
            // quantity will be set and added to today in db
            catalog.setQuantity(quantity);
            // business id needs to pulled from business being shopped

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
}
