package com.site.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider; // allows to turn to DB (to gets users)

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // disable csrf
        http
                .authorizeRequests()
                .antMatchers("/**/*.html", "/**/*.css", "/**/*.js").permitAll() // allow to get file
                .antMatchers("/books/lite","/books/{id}/{name}").hasRole("USER")
                //.anyRequest().authenticated() //allow to make request on any other url for any logged users
                .anyRequest().hasRole("ADMIN")  //allow only for logged user with ADMIN role
                .and()
                .formLogin().loginPage("/login").successHandler(customAuthenticationSuccessHandler).permitAll()
                .and()
                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}