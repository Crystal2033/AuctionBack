package com.debugteam.auction_test.database.repositories;

import com.debugteam.auction_test.database.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    Optional<ProductEntity> findOptionalById(String id);
    List<ProductEntity> findAllByName(String name);
    boolean existsById(String id);
    boolean existsByName(String name);
}
