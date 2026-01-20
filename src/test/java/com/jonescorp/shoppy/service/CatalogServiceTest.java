package com.jonescorp.shoppy.service;

import com.jonescorp.shoppy.model.Catalog;
import com.jonescorp.shoppy.repository.CatalogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceTest {

    @Mock
    private CatalogRepository catalogRepository;

    @InjectMocks
    private CatalogService catalogService;

//    Catalog catalogItem = new Catalog();
    Catalog catalogItem = mock(Catalog.class);

    @BeforeEach
    public void setup() {

//        catalogItem.setCatalogId(12345L);
        catalogItem.setQuantity(4);
        catalogItem.setPrice(4.5);
        catalogItem.setCategory("Category");
        catalogItem.setDescription("description");
        catalogItem.setName("name");
        catalogItem.setSku(1234);
        catalogItem.setBusinessId(12134L);

//        catalogRepository.save(catalogItem);
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

    @Test
    public void createCatalogItem() {
        catalogService.createCatalogItem(12345L,"name", "description", "category", 4.5, 1234, 4);

        ArgumentCaptor<Catalog> captor = ArgumentCaptor.forClass(Catalog.class);
        Mockito.verify(catalogRepository).save(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo("name");
        assertThat(captor.getValue().getQuantity()).isEqualTo(4);
//        assertThat(captor.getValue().getCatalogId()).isEqualTo(12345L);
        assertThat(captor.getValue().getDescription()).isEqualTo("description");
        assertThat(captor.getValue().getCategory()).isEqualTo("category");
        assertThat(captor.getValue().getPrice()).isEqualTo(4.5);
        assertThat(captor.getValue().getBusinessId()).isEqualTo(12134L);
    }
}