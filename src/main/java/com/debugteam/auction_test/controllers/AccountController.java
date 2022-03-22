package com.debugteam.auction_test.controllers;

import com.debugteam.auction_test.models.AccountRequest;
import com.debugteam.auction_test.models.AccountResponse;
import com.debugteam.auction_test.models.LotResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/account") // accounts
public class AccountController {


    @GetMapping("")
    public List<AccountResponse> getUsers() { // добавить аргументы

        return new ArrayList<AccountResponse>();
    }

    @PatchMapping("")
    public String changeUser() { // добавить аргументы
        return "1234";
    }

    @PostMapping("")
    public AccountResponse addUser(@RequestBody AccountRequest accountRequest) { // добавить аргументы
        return new AccountResponse();
    }

    @DeleteMapping("")
    public boolean deleteUser() {
        return true;
    }

    @GetMapping("/lots")
    public List<LotResponse> getUserLots() { // добавить аргументы

        return new ArrayList<>();
    }

    @PostMapping("/money")
    public String addMoney() // добавить аргументы
    {
        return "1234";
    }
}