package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.exceptions.UserAccessViolationException;
import com.debugteam.auction_test.models.*;

import java.util.List;

public interface AccountService {

    AccountDto addMoney(AccountRequest accountRequest) throws AccountNotExistsException; // Не нужно передавать String id?

    List<LotDto> getUserLots(String userId) throws AccountNotExistsException; // Не нужно передавать String id?

    List<BetDto> getUserBets(String userId) throws AccountNotExistsException;

    List<ProductDto> getUserProducts(String accountId) throws AccountNotExistsException;

    AccountDto getUser(String studentId) throws AccountNotExistsException; // Не нужно передавать String id?

    AccountDto getUserById(String studentId) throws AccountNotExistsException; // Не нужно передавать String id?

    void changeUser(AccountRequest studentRequest, String owner) throws AccountNotExistsException,
            UserAccessViolationException; //boolean

    void deleteUser(String studentId) throws AccountNotExistsException; //boolean

}
