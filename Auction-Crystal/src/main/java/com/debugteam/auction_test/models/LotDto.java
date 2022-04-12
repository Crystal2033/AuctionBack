package com.debugteam.auction_test.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
