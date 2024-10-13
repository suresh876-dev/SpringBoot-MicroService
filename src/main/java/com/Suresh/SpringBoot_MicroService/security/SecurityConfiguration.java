package com.Suresh.SpringBoot_MicroService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

     @Autowired
      JwtAuthFilter jwtAuthFilter;

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
    //Authorization using springboot security but real microservice we can't use these authrization with login page
    /*@Bean
    public SecurityFilterChain authorize(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf->csrf.disable())//disable means allow third party requests
                .authorizeHttpRequests(auth->{
                  //  auth.requestMatchers(HttpMethod.POST).hasAnyRole("MANAGER","ADMIN").
                   //  auth.requestMatchers(HttpMethod.POST,"/user/createUser").hasAnyRole("ADMIN")
                   // .requestMatchers("/user/**").hasAnyRole("USER","MANAGER","ADMIN")
                   //  .requestMatchers("/userapp/authenticate").permitAll()//without allow the request
                    //        .anyRequest().authenticated();

                     auth.anyRequest().permitAll();
                })//.formLogin(Customizer.withDefaults()) //ui login page with deault login page username and password browser based login
       // .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // im telling to my spring boot framewrok
       // whenver any requet come please make sure these filter called before these filter checks your json jwt token// here we are calling jwtauthfilter
        return httpSecurity.build();
    }*/

}
