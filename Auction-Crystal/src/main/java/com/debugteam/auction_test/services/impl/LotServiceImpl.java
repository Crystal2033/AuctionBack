package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.entities.LotEntity;
import com.debugteam.auction_test.database.repositories.AccountRepository;
import com.debugteam.auction_test.database.repositories.LotRepository;
import com.debugteam.auction_test.exceptions.LotExistsException;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.models.LotRequest;
import com.debugteam.auction_test.services.LotService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LotServiceImpl implements LotService {

    private final LotRepository lotRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper mapper;

    public LotServiceImpl(LotRepository lotRepository, AccountRepository accountRepository, ModelMapper mapper) {
        this.lotRepository = lotRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public List<LotDto> getSearchLots(String name)
    {
        return new ArrayList<LotDto>();
    }

    @Override
    public LotDto addLot(LotRequest lotRequest, String user_id) throws LotExistsException
    {
        // надо ли проверять остальные параметры? И нужны ли тут параметры?

        // Проверка именно такая?
        if (lotRequest.getName() != null && lotRepository.existsByName(lotRequest.getName())) {
            throw new LotExistsException();
        }

        // тут не берется почему-то
        AccountEntity accountEntity = accountRepository.getById(user_id);

        LotEntity newLot = mapper.map(lotRequest, LotEntity.class);
        newLot.setUser(accountEntity);
        lotRepository.save(newLot);

        return mapper.map(newLot, LotDto.class);
    }

    @Override
    public void deleteLot(String lotId)
    {

    }
}
