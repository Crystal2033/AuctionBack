package com.debugteam.auction_test.database.repositories;

import com.debugteam.auction_test.database.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

///////////////////////////////////////////////////////////////////////////////
// JpaRepository <с чем взаимодействует -- AccountEntity, ти ключа -- String> /
///////////////////////////////////////////////////////////////////////////////
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findOptionalById(String id);
    boolean existsById(String id);
}
