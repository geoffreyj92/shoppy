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

    public ArrayList<Catalog> getAll() {
        return catalogService.getAll();
    }

    public Optional<Catalog> getByUsernameOrId(Long id, String username) {
       return catalogService.getCatalogByUsernameOrUserId(id, username);
    }

    @PostMapping
    public Catalog createCatalogItem() {
        return catalogService.createCatalogItem();
    }

    @PutMapping
    public Catalog updateCatalogItem(Catalog catalog) {
        return null;
    }

    @DeleteMapping
    public boolean deleteCatalogItem(Catalog catalog) {
        return false;
    }

}
