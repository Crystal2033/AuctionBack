package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.*;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.models.LotRequest;

import java.util.List;

public interface LotService {

    List<LotDto> getSearchLots(String name) throws LotNotExistsException;

    LotDto addLot(LotRequest lotRequest, String userId) throws LotExistsException,
            AccountNotExistsException, ProductAlreadyInLotException;

    void deleteLot(String lotId, String userId) throws LotNotExistsException, UserAccessViolationException;

    List<LotDto> getLots();
}
