package com.in28minutes.springboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by IrianLaptop on 7/10/2017.
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    //Authentication: User-Roles mappings
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("user1").password("secret1")
                .roles("USER").and().withUser("admin1").password("secret1").roles("ADMIN");
    }

    //Authorization: Role -> Access mappings

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/surveys/**").hasRole("USER")
                .antMatchers("/users/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").hasRole("ADMIN")
                .and().csrf().disable().headers().frameOptions().disable();
    }
}
