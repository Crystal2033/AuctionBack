package com.debugteam.auction_test.security;

import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.repositories.AccountRepository;
import com.debugteam.auction_test.security.models.OurAuthToken;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class MainAuthFilter implements Filter {

    private static final String TOKEN_HEADER = "x-access-token";

    protected final AuthenticationFailureHandler failureHandler;

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    private List<RequestMatcher> requireAuthMatcher;

    public MainAuthFilter setRequireAuthMatcher(List<RequestMatcher> requireAuthMatcher) {
        this.requireAuthMatcher = requireAuthMatcher;
        return this;
    }

    public MainAuthFilter(AuthenticationFailureHandler failureHandler,
                          AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.failureHandler = failureHandler;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        if (requireAuth((HttpServletRequest) req)) {
            OurAuthToken token = tryAuth((HttpServletRequest) req, (HttpServletResponse) res);
            if (token == null) {
                failureHandler.onAuthenticationFailure(
                        (HttpServletRequest) req,
                        (HttpServletResponse) res,
                        new AuthenticationServiceException("Invalid user name or password")
                );
            } else {
                SecurityContextHolder.getContext().setAuthentication(token);
                chain.doFilter(req, res);
            }
        } else {
            chain.doFilter(req, res);
        }
    }

    @Nullable
    protected OurAuthToken tryAuth(HttpServletRequest req, HttpServletResponse res) {
        String token = req.getHeader(TOKEN_HEADER);

        Optional<AccountEntity> optionalUser = accountRepository.findOptionalBySecretToken(token);
        if (optionalUser.isEmpty()) {
            return null;
        }
        AccountEntity user = optionalUser.get();


        Collection<? extends GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority("BASE_USER")
        );

        return new OurAuthToken(user.getId(), user, authorities);
    }


    private boolean requireAuth(HttpServletRequest req) {
        return requireAuthMatcher.stream().anyMatch(requestMatcher -> requestMatcher.matches(req));
    }
}
