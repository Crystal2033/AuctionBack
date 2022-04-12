package com.debugteam.auction_test.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BetRequest {

    private String betId;
    private String userNickname;

    private Integer betSize;
}
