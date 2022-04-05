package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.AccountExistsException;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.AccountRequest;
import com.debugteam.auction_test.models.LotDto;

import java.util.List;

    public interface AccountService {

        AccountDto saveUser(AccountRequest studentRequest) throws AccountExistsException;

        List<LotDto> getUserLots(String userId) throws AccountExistsException;

        AccountDto addUser(AccountRequest studentRequest) throws AccountExistsException;

        AccountDto getUser(String studentId) throws AccountNotExistsException;

        void changeUser(AccountRequest studentRequest) throws AccountNotExistsException; //boolean

        void deleteUser(String studentId) throws AccountNotExistsException; //boolean

}
