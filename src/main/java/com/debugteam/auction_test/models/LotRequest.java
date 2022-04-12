package com.debugteam.auction_test.models;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LotRequest {
    private String lotId;
    private String name;
    private int startPrice;
    //private photo;
    //LocalDateTime timeExistence;
}
