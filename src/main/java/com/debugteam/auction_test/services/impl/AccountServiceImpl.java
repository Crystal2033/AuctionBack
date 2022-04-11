package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.repositories.AccountRepository;
import com.debugteam.auction_test.exceptions.AccountExistsException;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.AccountRequest;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.services.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final ModelMapper mapper;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(ModelMapper mapper, AccountRepository accountRepository) //DI работает.
    {
        this.mapper = mapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto addMoney(AccountRequest accountRequest) throws AccountNotExistsException //Текущему юзеру ничего не нужно?
    {
        if (accountRequest.getId() == null || !accountRepository.existsById(accountRequest.getId())) {
            throw new AccountNotExistsException();
        }

        AccountEntity account = accountRepository.getById(accountRequest.getId());
        account.setMoney(accountRequest.getMoney());
        accountRepository.save(account);
        return mapper.map(account, AccountDto.class);
        //return new AccountDto();
    }
    @Override
    public List<LotDto> getUserLots(String accountId) throws AccountExistsException
    {
        //TODO: Make
        return new ArrayList<LotDto>();
    }

    @Override
    public AccountDto addUser(AccountRequest accountRequest) throws AccountExistsException
    {
        if (accountRequest.getId() == null || !accountRepository.existsById(accountRequest.getId())) {
            throw new AccountExistsException();
        }

        AccountEntity newAccount = mapper.map(accountRequest, AccountEntity.class);
        accountRepository.save(newAccount);

        return mapper.map(newAccount, AccountDto.class);
    }

    @Override
    public AccountDto getUser(String accountId) throws AccountNotExistsException
    {
        Optional<AccountEntity> existedUser = accountRepository.findOptionalById(accountId);

        AccountEntity user = existedUser.orElseThrow(AccountNotExistsException::new);
        return mapper.map(user, AccountDto.class);
    }

    @Override
    public void changeUser(AccountRequest accountRequest) throws AccountNotExistsException //boolean
    {
        if (accountRequest.getId() == null || !accountRepository.existsById(accountRequest.getId())) {
            throw new AccountNotExistsException();
        }

        AccountEntity user = accountRepository.getById(accountRequest.getId());
        user.setId(accountRequest.getId());
        user.setNickname(accountRequest.getNickname());
        user.setMoney(accountRequest.getMoney());
        accountRepository.save(user);
    }

    @Override
    public void deleteUser(String accountId) throws AccountNotExistsException //boolean
    {
        Optional<AccountEntity> existedUser = accountRepository.findOptionalById(accountId);
        AccountEntity user = existedUser.orElseThrow(AccountNotExistsException::new);
        accountRepository.delete(user);
    }
}
