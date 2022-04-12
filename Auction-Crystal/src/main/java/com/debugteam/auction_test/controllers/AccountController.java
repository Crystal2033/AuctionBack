package com.debugteam.auction_test.controllers;

import com.debugteam.auction_test.exceptions.AccountExistsException;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.AccountRequest;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.models.ProductDto;
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
    public AccountDto getUser(OurAuthToken ourAuthToken) throws AccountNotExistsException { // добавить аргументы
||||||| 681a25a
    public AccountDto getUser(@RequestParam(value = "stringId") String stringId) throws AccountNotExistsException { // добавить аргументы
        //Был обычный string, мы поставили @PathVariable("id") и запрос был  @GetMapping("")
        //return new AccountDto();
        return accountService.getUser(ourAuthToken.getPrincipal().getId());
||||||| 681a25a
        return accountService.getUser(stringId);
    }

    @PatchMapping("")
    public void changeUser(@RequestBody AccountRequest accountRequest) throws AccountNotExistsException
    {
        accountService.changeUser(accountRequest);
    }

    @DeleteMapping("")
    public void deleteUser(OurAuthToken ourAuthToken) throws AccountNotExistsException
    {
        return "12312441";
    }


    // не нужен!
    @PostMapping("")
    public AccountDto addUser(@RequestBody AccountRequest accountRequest) throws AccountExistsException  { // добавить аргументы
        return accountService.addUser(accountRequest);
    }

    @DeleteMapping("")
    public void deleteUser(OurAuthToken ourAuthToken) throws AccountNotExistsException {
        accountService.deleteUser(ourAuthToken.getPrincipal().getId());
||||||| 681a25a
        return "12312441";
    }


    // не нужен!
    @PostMapping("")
    public AccountDto addUser(@RequestBody AccountRequest accountRequest) throws AccountExistsException  { // добавить аргументы
        return accountService.addUser(accountRequest);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") String userId, OurAuthToken ourAuthToken) throws AccountNotExistsException
    {
        accountService.deleteUser(userId);
    }

    @GetMapping("/lots")
    public List<LotDto> getUserLots(OurAuthToken ourAuthToken) throws AccountNotExistsException { // добавить аргументы #TODO: Спросить, что передавать в аргументы.
        return accountService.getUserLots(ourAuthToken.getPrincipal().getId());
    }

    @GetMapping("/products")
    public List<ProductDto> getUserProducts(OurAuthToken ourAuthToken) throws AccountNotExistsException { // добавить аргументы #TODO: Спросить, что передавать в аргументы.
        return accountService.getUserProducts(ourAuthToken.getPrincipal().getId());
||||||| 681a25a
    public List<LotDto> getUserLots(@RequestParam String userId) throws AccountExistsException { // добавить аргументы #TODO: Спросить, что передавать в аргументы.
        return accountService.getUserLots(userId);
    }

    @PostMapping("/money")
    public void addMoney(@RequestBody AccountRequest accountRequest, OurAuthToken ourAuthToken) throws AccountNotExistsException// добавить аргументы // token
    {
        accountRequest.setId(ourAuthToken.getPrincipal().getId());
        accountService.addMoney(accountRequest);
    }
}
