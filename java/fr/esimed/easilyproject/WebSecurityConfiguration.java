package fr.esimed.easilyproject;
/*
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Inject
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/").access("hasRole('USER') OR hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/signin")
                .usernameParameter("email").passwordParameter("password")
                .failureUrl("/signin?error=1")
                .defaultSuccessUrl("/")
                .permitAll()
                .and().logout()
                .permitAll();
    }
}*/