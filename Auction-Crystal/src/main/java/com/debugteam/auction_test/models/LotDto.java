package com.debugteam.auction_test.models;

import com.debugteam.auction_test.database.entities.AccountEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LotDto {

    String name;
    int startPrice;
    LocalDateTime addDate;

    //int currentBid; // == bets[0];
    //User owner; ????
    //int bidStep;
    //int lastBid;
    //Product product;
}
