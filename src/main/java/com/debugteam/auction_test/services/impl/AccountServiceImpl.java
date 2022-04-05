package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.exceptions.AccountExistsException;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.AccountRequest;
import com.debugteam.auction_test.services.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final ModelMapper mapper;

    public AccountServiceImpl(ModelMapper mapper)
    {
        this.mapper = mapper;
    }
    @Override
    public AccountDto saveUser(AccountRequest studentRequest) throws AccountExistsException
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
