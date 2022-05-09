package com.debugteam.auction_test.configurations;

import com.debugteam.auction_test.security.MainAuthFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MainAuthFilter mainAuthFilter;
    private final List<RequestMatcher> privateZones;

    public WebSecurityConfig(MainAuthFilter mainAuthFilter, List<RequestMatcher> privateZones)
    {
        this.mainAuthFilter = mainAuthFilter;
        this.privateZones = privateZones;
        //this.privateZones.add(new AndRequestMatcher(new AntPathRequestMatcher("/api/account/**")));
        this.privateZones.add(new AndRequestMatcher(new AntPathRequestMatcher("/api/bets/**")));
        this.privateZones.add(new AndRequestMatcher(new AntPathRequestMatcher("/api/products/**")));
        this.privateZones.add(new AndRequestMatcher(new AntPathRequestMatcher("/api/lots/add/**")));
        this.privateZones.add(new AndRequestMatcher(new AntPathRequestMatcher("/api/lots/delete/**")));


    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        //TODO: Поставить сюда privatezones

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Cache-Control", "Content-Type", "x-access-token"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));


        httpSecurity
                .csrf()
                .disable()
                .sessionManagement()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/api/registration/**").permitAll()
                .antMatchers("/api/account/get/**").permitAll()
                .antMatchers("/api/lots/all/**").permitAll()
                .antMatchers("/api/lots/search/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(
                        mainAuthFilter.setRequireAuthMatcher(this.privateZones),
                        UsernamePasswordAuthenticationFilter.class
                )
                .cors().configurationSource(request -> corsConfiguration);
    }
}