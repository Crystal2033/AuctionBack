package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.exceptions.AccountExistsException;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.AccountRequest;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.services.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final ModelMapper mapper;

    public AccountServiceImpl(ModelMapper mapper)
    {
        this.mapper = mapper;
    }

    @Override
    public void addMoney(String userId) throws AccountNotExistsException
    {

    }
    @Override
    public List<LotDto> getUserLots(String userId) throws AccountExistsException
    {
        return new ArrayList<LotDto>();

    }

    @Override
    public AccountDto addUser(AccountRequest studentRequest) throws AccountExistsException
    {
        return new AccountDto();
    }

    @Override
    public AccountDto getUser(String studentId) throws AccountNotExistsException
    {
        return new AccountDto();
    }

    @Override
    public void changeUser(AccountRequest studentRequest) throws AccountNotExistsException //boolean
    {

    }

    @Override
    public void deleteUser(String studentId) throws AccountNotExistsException //boolean
    {

    }
}
