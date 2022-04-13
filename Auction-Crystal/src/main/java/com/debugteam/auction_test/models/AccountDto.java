package com.debugteam.auction_test.models;

import lombok.Data;

@Data
public class AccountDto {
    private String id; //from db
    private String nickname;
    private String secretToken;
    private int money;
}
