package com.debugteam.auction_test.security.models;


import com.debugteam.auction_test.database.entities.AccountEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class OurAuthToken extends AbstractAuthenticationToken {

    private AccountEntity principal;
    private String userId;

    public OurAuthToken(String userId, AccountEntity principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.userId = userId;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}

