package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.LotExistsException;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.models.LotRequest;

import java.util.List;

public interface LotService {

    List<LotDto> getSearchLots(String name);

    LotDto addLot(LotRequest lotRequest, String user_id) throws LotExistsException;

    void deleteLot(String lotId);
}
