package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.LotExistsException;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.models.LotRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public interface LotServices {

    List<LotDto> getSearchLots(String name);

    LotDto addLot(LotRequest lotRequest) throws LotExistsException;

    void deleteLot(String lotId);
}
