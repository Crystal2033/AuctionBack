package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.entities.BetEntity;
import com.debugteam.auction_test.database.entities.ProductEntity;
import com.debugteam.auction_test.database.repositories.AccountRepository;
import com.debugteam.auction_test.database.repositories.BetRepository;
import com.debugteam.auction_test.exceptions.BetExistException;
import com.debugteam.auction_test.exceptions.BetNotExistException;
import com.debugteam.auction_test.models.BetDto;
import com.debugteam.auction_test.models.BetRequest;
import com.debugteam.auction_test.models.ProductDto;
import com.debugteam.auction_test.services.BetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BetServiceImpl implements BetService {
    private final BetRepository betRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper mapper;

    public BetServiceImpl(BetRepository betRepository, AccountRepository accountRepository, ModelMapper mapper) {
        this.betRepository = betRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public BetDto addBet(BetRequest betRequest, String userId) throws BetExistException {
        if (betRequest.getId() == null || betRepository.existsById(betRequest.getId())){
            throw new BetExistException();
        }

        AccountEntity accountEntity = accountRepository.getById(userId);

        BetEntity newBet = mapper.map(betRequest, BetEntity.class);
        newBet.setUser(accountEntity);
        betRepository.save(newBet);

        return mapper.map(newBet, BetDto.class);
    }

    @Override
    public void deleteBet(String betId) throws BetNotExistException{
        Optional<BetEntity> existedBet = betRepository.findOptionalById(betId);
        BetEntity product = existedBet.orElseThrow(BetNotExistException::new);
        betRepository.delete(product);
    }

    @Override
    public BetDto getBet(String betId) throws  BetNotExistException{
        Optional<BetEntity> existedBet = betRepository.findOptionalById(betId);
        BetEntity bet = existedBet.orElseThrow(BetNotExistException::new);
        return mapper.map(bet, BetDto.class);
    }

    @Override
    public List<BetDto> getBets(){
        List<BetEntity> betsEntity = betRepository.findAll();
        List<BetDto> betsDto = new ArrayList<>();

        for (BetEntity betEntity : betsEntity) {
            betsDto.add(mapper.map(betEntity, BetDto.class));
        }
        return betsDto;
    }

}
