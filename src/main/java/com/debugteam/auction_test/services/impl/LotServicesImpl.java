package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.database.repositories.LotRepository;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.models.LotRequest;
import com.debugteam.auction_test.services.LotServices;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LotServicesImpl implements LotServices {

    private final LotRepository lotRepository;
    private final ModelMapper mapper;

    public LotServicesImpl(LotRepository lotRepository, ModelMapper mapper) {
        this.lotRepository = lotRepository;
        this.mapper = mapper;
    }


    @Override
    public List<LotDto> getSearchLots(String name)
    {
        return new ArrayList<LotDto>();
    }

    @Override
    public LotDto addLot(LotRequest lotRequest)
    {

        return new LotDto();
    }

    @Override
    public void deleteLot(String lotId)
    {

    }
}
