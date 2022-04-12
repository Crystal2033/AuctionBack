package com.debugteam.auction_test.database.repositories;

import com.debugteam.auction_test.database.entities.LotEntity;
import com.debugteam.auction_test.database.entities.ProductEntity;
import com.debugteam.auction_test.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    boolean existsById(String id);
    boolean existsByName(String name);
}
