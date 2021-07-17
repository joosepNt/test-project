package com.example.testproject.config;

import com.example.testproject.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/add").authenticated()
                .antMatchers("/clients").authenticated()
                .antMatchers("/edit").authenticated()
                .antMatchers("/h2-console/**").permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and().userDetailsService(customUserDetailService);

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
