package com.debugteam.auction_test.controllers;

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

    @GetMapping("/lots")
    public List<LotResponse> getLots() { // добавить аргументы

        return new ArrayList<>();
    }

    @PostMapping("")
    public AccountResponse addUser() { // добавить аргументы
        return new AccountResponse();
    }

    @PatchMapping("")
    public String addMoney() // добавить аргументы
    {
        return "1234";
    }

    @DeleteMapping("")
    public boolean deleteUser() {
        return true;
    }
}