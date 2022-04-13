package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.entities.BetEntity;
import com.debugteam.auction_test.database.entities.LotEntity;
import com.debugteam.auction_test.database.entities.ProductEntity;
import com.debugteam.auction_test.database.repositories.AccountRepository;
import com.debugteam.auction_test.database.repositories.LotRepository;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.exceptions.UserAccessViolationException;
import com.debugteam.auction_test.models.*;
import com.debugteam.auction_test.services.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final ModelMapper mapper;
    private final AccountRepository accountRepository;
    private final LotRepository lotRepository;

    public AccountServiceImpl(ModelMapper mapper, AccountRepository accountRepository, LotRepository lotRepository) //DI работает.
    {
        this.mapper = mapper;
        this.accountRepository = accountRepository;
        this.lotRepository = lotRepository;
    }

    @Override
    public AccountDto addMoney(AccountRequest accountRequest) throws AccountNotExistsException //Текущему юзеру ничего не нужно?
    {
        if (accountRequest.getId() == null || !accountRepository.existsById(accountRequest.getId())) {
            throw new AccountNotExistsException();
        }

        AccountEntity account = accountRepository.getById(accountRequest.getId());
        account.setMoney(accountRequest.getMoney() + account.getMoney());
        accountRepository.save(account);
        return mapper.map(account, AccountDto.class);
    }

    @Override
    public List<LotDto> getUserLots(String accountId) throws AccountNotExistsException {
        Optional<AccountEntity> existedUser = accountRepository.findOptionalById(accountId);
        AccountEntity user = existedUser.orElseThrow(AccountNotExistsException::new);
        List<LotEntity> lotEntities = user.getUserLots();
        List<LotDto> lotsDto = new ArrayList<>();

        for (LotEntity lot : lotEntities) {
            lotsDto.add(mapper.map(lot, LotDto.class));
        }
        return lotsDto;
    }

    @Override
    public List<BetDto> getUserBets(String accountId) throws AccountNotExistsException {
        Optional<AccountEntity> existedUser = accountRepository.findOptionalById(accountId);

        AccountEntity user = existedUser.orElseThrow(AccountNotExistsException::new);
        List<BetEntity> betEntities = user.getUserBets();
        List<BetDto> betsDto = new ArrayList<>();

        for (BetEntity bet : betEntities) {
            betsDto.add(mapper.map(bet, BetDto.class));
        }
        return betsDto;
    }

    @Override
    public List<ProductDto> getUserProducts(String accountId) throws AccountNotExistsException {
        Optional<AccountEntity> existedUser = accountRepository.findOptionalById(accountId);
        AccountEntity user = existedUser.orElseThrow(AccountNotExistsException::new);

        List<ProductEntity> productsEntity = user.getUserProducts();

        List<ProductDto> productDto = new ArrayList<>();
        for (ProductEntity product : productsEntity) {
            productDto.add(mapper.map(product, ProductDto.class));
        }
        return productDto;
    }


    @Override
    public AccountDto getUser(String accountId) throws AccountNotExistsException {
        Optional<AccountEntity> existedUser = accountRepository.findOptionalById(accountId);

        AccountEntity user = existedUser.orElseThrow(AccountNotExistsException::new);
        return mapper.map(user, AccountDto.class);
    }

    @Override
    public void changeUser(AccountRequest accountRequest, String ownerId) throws AccountNotExistsException,
            UserAccessViolationException//boolean
    {
        if (accountRequest.getId() == null || !accountRepository.existsById(accountRequest.getId())) {
            throw new AccountNotExistsException();
        }

        AccountEntity ownerEntity = accountRepository.getById(ownerId);

        AccountEntity user = accountRepository.getById(accountRequest.getId());

        if (user != ownerEntity) {
            throw new UserAccessViolationException();
        }

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
