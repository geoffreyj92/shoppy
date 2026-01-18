package com.jonescorp.shoppy.controller;

import com.jonescorp.shoppy.model.Catalog;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class CatalogController {



    @GetMapping
    public ArrayList<Catalog> getAllByBusinessId(Integer businessId) {
        return null;
    }

    @PostMapping
    public Catalog createCatalogItem() {
        return null;
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
