package com.debugteam.auction_test.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRequest {
    private String id; // Для фронта
    //@JsonProperty("nickname")
    private String nickname;

    private String email;

    //@JsonProperty("password")
    private String password;
    private Integer money;
}