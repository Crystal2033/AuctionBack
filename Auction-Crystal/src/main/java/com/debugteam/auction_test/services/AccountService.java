package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.AccountExistsException;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.AccountRequest;
import com.debugteam.auction_test.models.LotDto;

import java.util.List;

    public interface AccountService {

        AccountDto addMoney(AccountRequest accountRequest) throws AccountNotExistsException; // Не нужно передавать String id?

        List<LotDto> getUserLots(String userId) throws AccountNotExistsException; // Не нужно передавать String id?

        AccountDto getUser(String studentId) throws AccountNotExistsException; // Не нужно передавать String id?

        void changeUser(AccountRequest studentRequest) throws AccountNotExistsException; //boolean

        void deleteUser(String studentId) throws AccountNotExistsException; //boolean

}
