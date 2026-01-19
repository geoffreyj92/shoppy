package com.jonescorp.shoppy.service;

import com.jonescorp.shoppy.model.Catalog;
import com.jonescorp.shoppy.repository.CatalogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceTest {

    @Mock
    private CatalogRepository catalogRepository;

    @InjectMocks
    private CatalogService catalogService;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testFindAll() {
        Catalog catalogItem = new Catalog();
        catalogItem.setCatalogId(12345L);
        catalogItem.setQuantity(4);
        catalogItem.setPrice(4.5);
        catalogItem.setCategory("Category");
        catalogItem.setDescription("Description");
        catalogItem.setName("Name");
        catalogItem.setBusinessId(12134L);
        Catalog catalogItem2 = new Catalog();
        catalogItem2.setCatalogId(12345L);
        catalogItem2.setQuantity(4);
        catalogItem2.setPrice(4.5);
        catalogItem2.setCategory("Category");
        catalogItem2.setDescription("Description");
        catalogItem2.setName("Name");
        catalogItem2.setBusinessId(12134L);

        List<Catalog> catalogs = new ArrayList<>();
        catalogs.add(catalogItem);
        catalogs.add(catalogItem2);

        when(catalogRepository.findAll()).thenReturn((catalogs));

        List result = catalogService.getCatalogs();

        assertEquals(result, catalogs);

    }
}
