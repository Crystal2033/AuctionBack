package com.debugteam.auction_test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountRequest {
    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("password")
    private String password;
    private int money;
    private int key; // TODO: подумать о типе данных
}