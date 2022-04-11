package com.debugteam.auction_test.models;

import java.util.List;

public class Lot {

    private int id; // уникальный id
    private int startedPrice;
    private int auctionTime; // подумать над типом (TimeStump LocalDateTime)
    private int step;

    //private User owner;
    private List<Product> products;
    private List<Bet> Bets; // сортированный список @oneToMany
    //пользователю в topBet, если ставка не прошла (перед обновлением)

}
