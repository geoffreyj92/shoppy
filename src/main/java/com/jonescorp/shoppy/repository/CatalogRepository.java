package com.jonescorp.shoppy.repository;

import com.jonescorp.shoppy.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    Optional<Catalog> getAllByDate(Date date);

    Optional<Catalog> findByUsername(String username);
}
