package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.entities.BetEntity;
import com.debugteam.auction_test.database.entities.LotEntity;
import com.debugteam.auction_test.database.repositories.AccountRepository;
import com.debugteam.auction_test.database.repositories.BetRepository;
import com.debugteam.auction_test.database.repositories.LotRepository;
import com.debugteam.auction_test.exceptions.BetExistException;
import com.debugteam.auction_test.exceptions.BetNotExistException;
import com.debugteam.auction_test.exceptions.BetOnOwnLotException;
import com.debugteam.auction_test.exceptions.LotNotExistsException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.BetDto;
import com.debugteam.auction_test.models.BetRequest;
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
    private final LotRepository lotRepository;
    private final ModelMapper mapper;

    public BetServiceImpl(BetRepository betRepository, AccountRepository accountRepository, LotRepository lotRepository, ModelMapper mapper) {
        this.betRepository = betRepository;
        this.accountRepository = accountRepository;
        this.lotRepository = lotRepository;
        this.mapper = mapper;
    }

    @Override
    public BetDto addBet(BetRequest betRequest, String userId) throws BetExistException, LotNotExistsException,
            BetOnOwnLotException {
        if (betRequest.getId() == null || betRepository.existsById(betRequest.getId())){ //мб, после || не нужно.
            throw new BetExistException();
        }
        //TODO: проверять на наличие денег для ставки на данный товар. И снимать у юзера деньги. И весь механизм
        Optional<AccountEntity> existedUser = accountRepository.findOptionalById(userId);
        AccountEntity accountEntity = existedUser.orElseThrow(LotNotExistsException::new); //ОТЛАДОЧНАЯ СТРОКА, УБРАТЬ!!!
        //4028b88180193384018019339fa20000
        Optional<LotEntity> existedLot = lotRepository.findOptionalById(betRequest.getLotId());
        LotEntity lotEntity = existedLot.orElseThrow(LotNotExistsException::new);

        if (lotEntity.getUser() == accountEntity){ //Мы не можем поставить ставку на свой же лот, нет никакого смысла.
            throw new BetOnOwnLotException();
        }

        BetEntity newBet = mapper.map(betRequest, BetEntity.class);

        newBet.setLot(lotEntity);
        newBet.setUser(accountEntity);

        //AccountDto userDto = mapper.map(accountEntity, AccountDto.class);
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
