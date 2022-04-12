package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.entities.LotEntity;
import com.debugteam.auction_test.database.repositories.AccountRepository;
import com.debugteam.auction_test.database.repositories.LotRepository;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.exceptions.LotExistsException;
import com.debugteam.auction_test.exceptions.LotNotExistsException;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.models.LotRequest;
import com.debugteam.auction_test.services.LotService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LotServicesImpl implements LotService {

    private final LotRepository lotRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper mapper;

    public LotServicesImpl(LotRepository lotRepository, AccountRepository accountRepository, ModelMapper mapper) {
        this.lotRepository = lotRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }


    @Override
    public List<LotDto> getSearchLots(String name) throws LotNotExistsException {
        List<LotEntity> foundEntities = lotRepository.findAllByName(name);
//        if(name == null || (foundEntities.size() == 0)){
//            throw new LotNotExistsException();
//        }
        List<LotDto> listLots = new ArrayList<LotDto>();

        for (LotEntity iter : foundEntities){
            listLots.add(mapper.map(iter,LotDto.class));
        }
        return listLots;
    }

    @Override
    public LotDto addLot(LotRequest lotRequest, String userId) throws LotExistsException, AccountNotExistsException {
        if (lotRequest.getLotId() == null || lotRepository.existsById(lotRequest.getLotId())){
            throw new LotExistsException();
        }
        LotEntity lot = mapper.map(lotRequest, LotEntity.class);

        Optional<AccountEntity> existedUser = accountRepository.findOptionalById(userId);
        AccountEntity userEntity = existedUser.orElseThrow(AccountNotExistsException::new);

        lot.setUser(userEntity);
        lotRepository.save(lot);

        return mapper.map(lot, LotDto.class);
    }

    @Override
    public void deleteLot(String lotId) throws LotNotExistsException {
        Optional<LotEntity> existedLot = lotRepository.findOptionalById(lotId);
        LotEntity lot = existedLot.orElseThrow(LotNotExistsException::new);
        lotRepository.delete(lot);
    }
}
