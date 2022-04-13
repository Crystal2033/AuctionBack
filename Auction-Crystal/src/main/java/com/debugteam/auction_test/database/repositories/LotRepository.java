package com.debugteam.auction_test.database.repositories;

import com.debugteam.auction_test.database.entities.LotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LotRepository extends JpaRepository<LotEntity, String> {
    Optional<LotEntity> findOptionalById(String id);

    //Optional<LotEntity> findOptionalByUser_id(String user_id);
    List<LotEntity> findAllByName(String name);

    boolean existsById(String id);

    boolean existsByName(String name);
}
