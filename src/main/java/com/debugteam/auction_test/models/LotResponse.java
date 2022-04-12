package com.debugteam.auction_test.models;

import java.time.LocalDateTime;

public class LotResponse {
    int currentBid; // == bets[0];
    LocalDateTime start_time;
    User owner;
    int bidStep;
    int lastBid;

    Product product;

}
