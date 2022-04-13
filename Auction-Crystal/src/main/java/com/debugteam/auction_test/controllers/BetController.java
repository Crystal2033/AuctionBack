package com.debugteam.auction_test.controllers;

import com.debugteam.auction_test.exceptions.*;
import com.debugteam.auction_test.models.BetDto;
import com.debugteam.auction_test.models.BetRequest;
import com.debugteam.auction_test.security.models.OurAuthToken;
import com.debugteam.auction_test.services.BetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bets")
public class BetController {

    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @GetMapping("/{id}")
    public BetDto getBet(@PathVariable("id") String betId) throws BetNotExistException {
        return betService.getBet(betId);
    }

    @GetMapping("")
    public List<BetDto> getBets() {
        return betService.getBets();
    }

    @PostMapping("")
    public BetDto addBet(@RequestBody BetRequest newBet, OurAuthToken authToken) throws BetExistException,
            LotNotExistsException, BetOnOwnLotException, NotEnoughMoneyException {
        return betService.addBet(newBet, authToken.getPrincipal().getId());
    }

    @DeleteMapping("/{id}")
    public void deleteBet(@PathVariable("id") String betId, OurAuthToken authToken) throws BetNotExistException,
            UserAccessViolationException//but its strange. How did yoi get id of not existing bet.
    {

        betService.deleteBet(betId, authToken.getPrincipal().getId());
    }
}

