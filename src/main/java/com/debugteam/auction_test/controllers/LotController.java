package com.debugteam.auction_test.controllers;


import com.debugteam.auction_test.models.BetRequest;
import com.debugteam.auction_test.models.LotResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/lot")
public class LotController {

    @GetMapping
    public List<LotResponse> getLots()
    {
        return new ArrayList<>();
    }

    @GetMapping("/search")
    public List<LotResponse> getSearchLots()
    {
        return new ArrayList<>();
    }


    @PostMapping
    public LotResponse addLot() {
        return new LotResponse();
    }

    @PatchMapping
    public int addBetOnLot(BetRequest new_bet)
    {
        return 0;
    }

    @DeleteMapping("/{id}")
    public int deleteLot(@PathVariable int id)
    {
        return 0;
    }

}
