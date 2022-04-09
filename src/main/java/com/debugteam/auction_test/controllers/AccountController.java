package com.debugteam.auction_test.controllers;

import com.debugteam.auction_test.exceptions.AccountExistsException;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.AccountRequest;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts") // accounts (Был account)
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("")
    public AccountDto getUser(String stringId) throws AccountNotExistsException { // добавить аргументы

        //return new AccountDto();
        return accountService.getUser(stringId);
    }

    @PatchMapping("")
    public void changeUser(@RequestBody AccountRequest accountRequest) throws AccountNotExistsException
    {
        accountService.changeUser(accountRequest);
    }

    @PutMapping("")
    public String testPostman()
    {
        return "12312441";
    }

    @PostMapping("")
    public AccountDto addUser(@RequestBody AccountRequest accountRequest) throws AccountExistsException  { // добавить аргументы
        return accountService.addUser(accountRequest);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") String userId) throws AccountNotExistsException
    {
        accountService.deleteUser(userId);
    }

    @GetMapping("/lots")
    public List<LotDto> getUserLots(String userId) throws AccountExistsException{ // добавить аргументы #TODO: Спросить, что передавать в аргументы.

        return accountService.getUserLots(userId);
    }

    @PostMapping("/money")
    public void addMoney(@RequestBody AccountRequest accountRequest) throws AccountNotExistsException// добавить аргументы // token
    {
        accountService.addMoney(accountRequest);
    }
}