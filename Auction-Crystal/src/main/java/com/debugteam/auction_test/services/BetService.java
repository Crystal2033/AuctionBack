package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.BetExistException;
import com.debugteam.auction_test.exceptions.BetNotExistException;
import com.debugteam.auction_test.exceptions.BetOnOwnLotException;
import com.debugteam.auction_test.exceptions.LotNotExistsException;
import com.debugteam.auction_test.models.BetDto;
import com.debugteam.auction_test.models.BetRequest;

import java.util.ArrayList;
import java.util.List;

public interface BetService {

    BetDto addBet(BetRequest newBet, String userId) throws BetExistException, LotNotExistsException, BetOnOwnLotException;

    void deleteBet(String betId) throws BetNotExistException;

    BetDto getBet(String betId) throws  BetNotExistException;

    List<BetDto> getBets();
}
