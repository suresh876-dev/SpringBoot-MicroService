package com.Suresh.SpringBoot_MicroService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authProvider =  new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain authorize(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf->csrf.disable())//disable means allow third party requests
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/user/getAllUsers").hasAnyRole("ADMIN");
                    auth.requestMatchers("/userapp/authenticate").permitAll()//without allow the request
                            .anyRequest().authenticated();
                }).formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

}
