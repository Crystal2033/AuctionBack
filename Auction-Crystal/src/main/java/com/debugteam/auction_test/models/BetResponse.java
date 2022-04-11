package com.debugteam.auction_test.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BetResponse {
//    public BetResponse()
//    {
//        userNickname = "Tom Response";
//        lotId = 123;
//        betSize = 2000;
//    }
    private String userNickname;
    private Integer lotId;
    private Integer betSize;
}
