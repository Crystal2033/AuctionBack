package com.debugteam.auction_test.controllers;

import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.exceptions.UserAccessViolationException;
import com.debugteam.auction_test.models.*;
import com.debugteam.auction_test.security.models.OurAuthToken;
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
    public AccountDto getUser(OurAuthToken authToken) throws AccountNotExistsException {
        return accountService.getUser(authToken.getPrincipal().getId());
    }

    @PatchMapping("")
    public void changeUser(@RequestBody AccountRequest accountRequest, OurAuthToken authToken) throws AccountNotExistsException,
            UserAccessViolationException {
        accountService.changeUser(accountRequest, authToken.getPrincipal().getId());
    }

    @DeleteMapping("")
    public void deleteUser(OurAuthToken ourAuthToken) throws AccountNotExistsException {
        accountService.deleteUser(ourAuthToken.getPrincipal().getId());
    }

    @GetMapping("/lots")
    public List<LotDto> getUserLots(OurAuthToken authToken) throws AccountNotExistsException {
        return accountService.getUserLots(authToken.getPrincipal().getId());
    }

    @GetMapping("/bets")
    public List<BetDto> getUserBets(OurAuthToken authToken) throws AccountNotExistsException {
        return accountService.getUserBets(authToken.getPrincipal().getId());
    }

    @GetMapping("/products")
    public List<ProductDto> getUserProducts(OurAuthToken ourAuthToken) throws AccountNotExistsException {
        return accountService.getUserProducts(ourAuthToken.getPrincipal().getId());
    }

    @PostMapping("/money")
    public void addMoney(@RequestBody AccountRequest accountRequest, OurAuthToken ourAuthToken) throws AccountNotExistsException// добавить аргументы // token
    {
        accountRequest.setId(ourAuthToken.getPrincipal().getId());
        accountService.addMoney(accountRequest);
    }
}