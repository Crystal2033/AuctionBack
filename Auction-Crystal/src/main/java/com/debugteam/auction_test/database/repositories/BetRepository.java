package com.debugteam.auction_test.database.repositories;

import com.debugteam.auction_test.database.entities.BetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BetRepository extends JpaRepository<BetEntity, String> {
    Optional<BetEntity> findOptionalById(String betId);

    boolean existsById(String betId); //Поиск по лоту, где id такой-то и макс-значение data такое-то.
    //BetEntity findTopByAddDateDescAndByOrderByLotId(String lotId); //TODO: посмотреть, как работает и работает ли.
}
