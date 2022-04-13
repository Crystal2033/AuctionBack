package com.debugteam.auction_test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "You have no permit to do this.")
public class UserAccessViolationException extends Exception {
}
