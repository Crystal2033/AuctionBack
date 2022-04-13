package com.debugteam.auction_test.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LotRequest {
    private String id;
    private List<String> productsId;
    private String name;
    private int startPrice;
    //private photo;
    //LocalDateTime timeExistence;
}
