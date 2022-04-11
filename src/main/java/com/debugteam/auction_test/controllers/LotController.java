package com.debugteam.auction_test.controllers;


import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.models.LotRequest;
import com.debugteam.auction_test.services.LotServices;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/lots")
public class LotController {

    private final LotServices lotServices;

    public LotController(LotServices lotServices) {
        this.lotServices = lotServices;
    }


    @GetMapping("")
    public List<LotDto> getSearchLots(@RequestParam("name") String name) {
        return new ArrayList<>();
    }


    @PostMapping
    public LotDto addLot(@RequestBody LotRequest lotRequest) {
        return lotServices.addLot(lotRequest);
    }

//    @PatchMapping
//    public int addBetOnLot(BetRequest new_bet) { // в контроллере ставок
//        return 0;
//    }

    @PutMapping
    public String addBetOnLot() { // в контроллере ставок
        return "Success";
    }

    @DeleteMapping("/{id}")
    public void deleteLot(@PathVariable int id) {
    }

}
