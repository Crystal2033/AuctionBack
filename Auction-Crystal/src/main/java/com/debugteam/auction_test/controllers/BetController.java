package com.debugteam.auction_test.controllers;

import com.debugteam.auction_test.exceptions.BetNotExistException;
import com.debugteam.auction_test.models.BetRequest;
import com.debugteam.auction_test.models.BetDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bets")
public class BetController {
    private HashMap<Integer, BetRequest> savedBets = new HashMap<>();

    @GetMapping("/{id}")
    public BetDto getBet(@PathVariable("id") Integer betId) throws BetNotExistException {
//        if (!savedBets.containsKey(betId)) {
//            throw new BetNotExistException();
//        }
//        BetDto foundBet = convertToResponse(savedBets.get(betId));
//        return foundBet;
        return new BetDto();
    }

    @GetMapping("")
    public ArrayList<BetDto> getBets() {
//        ArrayList<BetDto> result = new ArrayList<>();
//        for (Map.Entry<Integer, BetRequest> entry : savedBets.entrySet()) {
//            BetDto betResp = convertToResponse(entry.getValue());
//            result.add(betResp);
//        }
//        return result;
        return new ArrayList<>();
    }

    @PostMapping("")
    public Integer addBet(BetRequest newBet) {
//        Integer id = savedBets.size();
//        savedBets.put(id, newBet);
//        return id;
        return 1234;
    }

    @DeleteMapping("/{id}")
    public Integer deleteBet(@PathVariable("id") Integer betId) throws BetNotExistException //but its strange. How did yoi get id of not existing bet.
    {
//        if (!savedBets.containsKey(betId)) {
//            throw new BetNotExistException();
//        }
//        savedBets.remove(betId);
//        return betId;
        return 123;
    }

    ///////////////////////////////////////////////////////////////////////////
    //                      private
    ///////////////////////////////////////////////////////////////////////////
//    private BetDto convertToResponse(BetRequest betReq) {
//        BetDto betResp = new BetDto();
//        betResp.setBetSize(betReq.getBetSize());
//        betResp.setLotId(betReq.getLotId());
//        betResp.setUserNickname(betReq.getUserNickname());
//        return betResp;
//    }

}
