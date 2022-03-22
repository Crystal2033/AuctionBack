package com.debugteam.auction_test.controllers;


import com.debugteam.auction_test.models.BetRequest;
import com.debugteam.auction_test.models.LotResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/lots")
public class LotController {

    @GetMapping("")
    public List<LotResponse> getSearchLots(@RequestParam("name") String name) {
        return new ArrayList<>();
    }


    @PostMapping
    public LotResponse addLot() {
        return new LotResponse();
    }

//    @PatchMapping
//    public int addBetOnLot(BetRequest new_bet) { // в контроллере ставок
//        return 0;
//    }

    @DeleteMapping("/{id}")
    public int deleteLot(@PathVariable int id) {
        return 0;
    }

}
