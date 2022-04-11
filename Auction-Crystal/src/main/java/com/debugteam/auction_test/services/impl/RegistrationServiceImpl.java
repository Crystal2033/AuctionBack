package com.debugteam.auction_test.services.impl;


import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.repositories.AccountRepository;
import com.debugteam.auction_test.exceptions.AccountExistsException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.RegistrationParamsRequest;
import com.debugteam.auction_test.services.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final ModelMapper mapper;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(ModelMapper mapper, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AccountDto signup(RegistrationParamsRequest registrationParamsRequest) throws AccountExistsException {
        Optional<AccountEntity> existedUser = accountRepository.findOptionalByEmail(registrationParamsRequest.getEmail());
        if (existedUser.isPresent()) {
            throw new AccountExistsException();
        }

        AccountEntity user = mapper.map(registrationParamsRequest, AccountEntity.class);
        String password = passwordEncoder.encode(user.getPassword() + "salt");

        user.setPassword(password);
        user.setMoney(0);
        accountRepository.save(user);
        return mapper.map(user, AccountDto.class);
    }
}