package com.debugteam.auction_test.database.repositories;

import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.entities.LotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LotRepository extends JpaRepository<LotEntity, String> {
    Optional<LotEntity> findOptionalById(String id);
    boolean existsById(String id);
}
