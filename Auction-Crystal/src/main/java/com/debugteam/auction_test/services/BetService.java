package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.*;
import com.debugteam.auction_test.models.BetDto;
import com.debugteam.auction_test.models.BetRequest;

import java.util.List;

public interface BetService {

    BetDto addBet(BetRequest newBet, String userId) throws BetExistException, LotNotExistsException,
            BetOnOwnLotException, NotEnoughMoneyException;

    void deleteBet(String betId, String userId) throws BetNotExistException, UserAccessViolationException;

    BetDto getBet(String betId) throws BetNotExistException;

    List<BetDto> getBets();
}
