package com.Suresh.SpringBoot_MicroService.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// if any request come along with token please call these jwtauthfilter
@Configuration
public class JwtAuthFilter extends OncePerRequestFilter { // which is called automatically by springboot framwork without client knowing

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

// to check authorization of jwt token
    // validate the role from jwt token which has admin ,user,manger role or not
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token= null;
        String username=null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username= jwtUtils.extractUsername(token);
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtUtils.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userDetails,
                        null,
                        userDetails.getAuthorities());
                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authtoken); //it check authroiation of client //securitycontextholder willapply
                //for your request
            }
        }
        filterChain.doFilter(request,response);
    }
}
/*

        Client Logs In: Sends credentials to Spring Boot.
        JWT Generation: If the credentials are valid, a JWT is generated and sent back to the client.
        Token Storage: The client stores the JWT.
        Sending Token with Requests: The client sends the token in the Authorization header for every request.
        JWT Validation: The Spring Boot application validates the JWT in a filter.
        Authentication & Authorization: Based on the JWT claims, Spring Security handles authorization for the request.
        Response: The server responds with the requested data if authentication and authorization succeed.    */
