package com.jonescorp.shoppy.controller;

import com.jonescorp.shoppy.model.Catalog;
import com.jonescorp.shoppy.service.CatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }


    @GetMapping
    public Optional<Catalog> getAllByBusinessId(Long businessId) {
        return catalogService.getAllCatalogByBusinessId(businessId);
    }

    @GetMapping("/allItems")
    public ArrayList<Catalog> getAll() {
        return catalogService.getAll();
    }

    @PostMapping("/username/catalogItems/{catalogId}")
    public Catalog createCatalogItem(@RequestBody
                                         @PathVariable Long catalogId,
                                     String name,
                                     String description,
                                     String category,
                                     Double price,
                                     Integer sku,
                                     Integer quantity) {
        return catalogService.createCatalogItem(catalogId, name, description, category, price, sku, quantity);
    }

    @PutMapping("/username/catalogItems/{catalogId}")
    public Catalog updateCatalogItem(@RequestBody @PathVariable Long catalogId,
                                     String name,
                                     String description,
                                     String category,
                                     Double price,
                                     Integer quantity) {
        return catalogService.updateCatalogItem(catalogId, name, description, category, price, quantity);
    }

    @DeleteMapping
    public boolean deleteCatalogItem(Catalog catalog) {
        return false;
    }

}
