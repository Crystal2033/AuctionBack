package com.debugteam.auction_test.models;

import java.util.List;

public class Lot {
    private User owner;
    private List<Product> products;
    private int startedPrice;
    private List<Bet> Bets; // сортированный список @oneToMany
    //пользователю в topBet, если ставка не прошла (перед обновлением)
    private int auctionTime; // подумать над типом (TimeStump LocalDateTime)
    private int step;
    private int id; // уникальный id

}
