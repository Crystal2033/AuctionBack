package com.debugteam.auction_test.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class LotRequest {
    String name;
    int startPrice;
    //LocalDateTime timeExistence;
}
