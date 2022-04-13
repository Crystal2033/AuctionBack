package com.debugteam.auction_test.controllers;

import com.debugteam.auction_test.exceptions.AccountExistsException;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.exceptions.UserAccessViolationException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.RegistrationParamsRequest;
import com.debugteam.auction_test.services.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/signup")
    public AccountDto signup(@RequestBody RegistrationParamsRequest params) throws AccountExistsException {
        return registrationService.signup(params);
    }

    @PostMapping("/login")
    public AccountDto login(@RequestBody RegistrationParamsRequest params) throws AccountNotExistsException,
            UserAccessViolationException {
        return registrationService.login(params);
    }
}
