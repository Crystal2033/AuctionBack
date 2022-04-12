package com.debugteam.auction_test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Product already exists.")
public class ProductExistsException extends Exception {
}
