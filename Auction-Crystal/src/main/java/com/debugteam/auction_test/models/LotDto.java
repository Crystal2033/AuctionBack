package com.debugteam.auction_test.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LotDto {

    private String lotId;

    private String name;
    private int startPrice;
    private int bidStep;
    private List<BetDto> bets;
    //private List<ProductDto> products

    //int lastBid; в бд у ставок узнаем, наверное.
}