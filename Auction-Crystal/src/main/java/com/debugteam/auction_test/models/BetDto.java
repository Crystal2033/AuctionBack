package com.debugteam.auction_test.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BetDto {

    private String id;
    //private String userNickname; Для этого есть id.
    private Integer betSize;

    private LotDto lotDto;
    private AccountDto accountDto;
}
