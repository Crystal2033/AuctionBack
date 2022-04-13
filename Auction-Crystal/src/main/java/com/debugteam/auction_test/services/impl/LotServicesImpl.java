package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.entities.LotEntity;
import com.debugteam.auction_test.database.entities.ProductEntity;
import com.debugteam.auction_test.database.repositories.AccountRepository;
import com.debugteam.auction_test.database.repositories.LotRepository;
import com.debugteam.auction_test.database.repositories.ProductRepository;
import com.debugteam.auction_test.exceptions.*;
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
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public LotServicesImpl(LotRepository lotRepository, AccountRepository accountRepository, ProductRepository productRepository, ModelMapper mapper) {
        this.lotRepository = lotRepository;
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }


    @Override
    public List<LotDto> getSearchLots(String name) throws LotNotExistsException {
        List<LotEntity> foundEntities = lotRepository.findByNameContainingIgnoreCaseOrderByName(name);
//        if(name == null || (foundEntities.size() == 0)){
//            throw new LotNotExistsException();
//        }
        List<LotDto> listLots = new ArrayList<LotDto>();

        for (LotEntity iter : foundEntities) {
            listLots.add(mapper.map(iter, LotDto.class));
        }
        return listLots;
    }

    @Override
    public List<LotDto> getLots() {
        List<LotEntity> lotsEntity = lotRepository.findAll();

        List<LotDto> listLots = new ArrayList<LotDto>();

        for (LotEntity iter : lotsEntity) {
            listLots.add(mapper.map(iter, LotDto.class));
        }
        return listLots;
    }

    //Надо добавить productId, но у нас много продуктов, поэтому нужно передавать как-то лист продуктов.
    @Override
    public LotDto addLot(LotRequest lotRequest, String userId) throws LotExistsException, AccountNotExistsException
            , ProductAlreadyInLotException {
        if (lotRequest.getId() == null || lotRepository.existsById(lotRequest.getId())) {
            throw new LotExistsException();
        }
        LotEntity lot = mapper.map(lotRequest, LotEntity.class);

        Optional<AccountEntity> existedUser = accountRepository.findOptionalById(userId);
        AccountEntity userEntity = existedUser.orElseThrow(AccountNotExistsException::new);

        List<ProductEntity> productsEntity = new ArrayList<>();
        for (String productId : lotRequest.getProductsId()) {
            ProductEntity productEntity = productRepository.getById(productId);
            if (productEntity.getLot() != null) {
                throw new ProductAlreadyInLotException();
            }

            productEntity.setLot(lot);
            productsEntity.add(productEntity);
        }
        lot.setLotProducts(productsEntity);
        lot.setUser(userEntity);
        //TODO: Проход в цикле по списку строк-id товаров из lotRequest.
        //Здесь все то же самое, что у аккаунта и лота. То есть у нас здесь много лотов и один аккаунт. А нам нужно
        //сделать много продуктов и один лот.


        lotRepository.save(lot);

        return mapper.map(lot, LotDto.class);
    }

    @Override
    public void deleteLot(String lotId, String userId) throws LotNotExistsException, UserAccessViolationException {
        Optional<LotEntity> existedLot = lotRepository.findOptionalById(lotId);
        LotEntity lot = existedLot.orElseThrow(LotNotExistsException::new);
        AccountEntity accountEntity = accountRepository.getById(userId);
        if (lot.getUser() != accountEntity) {
            throw new UserAccessViolationException();
        }
        lotRepository.delete(lot);
    }
}
