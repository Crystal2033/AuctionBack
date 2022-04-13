package com.debugteam.auction_test.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Bet already exists.")
public class BetExistException extends Exception {
}
