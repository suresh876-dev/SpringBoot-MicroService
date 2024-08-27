package com.Suresh.SpringBoot_MicroService.controller;

import com.Suresh.SpringBoot_MicroService.dto.UserAuthenticateDto;
import com.Suresh.SpringBoot_MicroService.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/userapp")
public class UserAuthenticateController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping(value = "/authenticate",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> authenticate(@RequestBody UserAuthenticateDto userAuthenticateDto)
    {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userAuthenticateDto.getUsername(),userAuthenticateDto.getPassword()));
            String jwtToken = jwtUtils.generateToken(userAuthenticateDto.getUsername());

            userAuthenticateDto.setToken(jwtToken);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        catch (AuthenticationException e)
        {
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }

    }
}
