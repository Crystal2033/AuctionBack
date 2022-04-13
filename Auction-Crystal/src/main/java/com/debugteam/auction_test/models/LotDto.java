package com.debugteam.auction_test.models;

import lombok.Data;

import java.util.List;

@Data
public class LotDto {

    private String id;

    private String name;
    private int startPrice;
    private int bidStep;
    private List<BetDto> lotBets;
    private List<ProductDto> lotProducts;

    //int lastBid; в бд у ставок узнаем, наверное.
}