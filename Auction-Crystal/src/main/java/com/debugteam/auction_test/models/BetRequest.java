package com.debugteam.auction_test.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BetRequest {

    private String id;
    private String lotId; // Куда помещаем ставку, в какой лот.

    private Integer betSize;
}
