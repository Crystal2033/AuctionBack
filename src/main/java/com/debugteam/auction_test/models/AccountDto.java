package com.debugteam.auction_test.models;

import lombok.Data;

@Data
public class AccountDto {
    private String nickname;
    private int money;
    private int key; // TODO: подумать о типе данных // our database ID
}
