package com.debugteam.auction_test.services;


import com.debugteam.auction_test.exceptions.AccountExistsException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.RegistrationParamsRequest;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    AccountDto signup(RegistrationParamsRequest registrationParamsRequest) throws AccountExistsException;
}