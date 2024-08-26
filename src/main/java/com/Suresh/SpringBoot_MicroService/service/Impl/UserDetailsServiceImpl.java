package com.Suresh.SpringBoot_MicroService.service.Impl;

import com.Suresh.SpringBoot_MicroService.entity.UserEntity;
import com.Suresh.SpringBoot_MicroService.repository.UserEntityRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // write the code to load user from UserEntity table using Repository API

       List<UserEntity> userEntities = userEntityRepository.findbyUserName(username);
       if(userEntities==null||userEntities.size()==0)
       {
           throw new UsernameNotFoundException(username);
       }
       UserEntity userEntity = userEntities.get(0);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String roles = userEntity.getRoles();//USER,MANAGER
        String roleArray[] = roles.split(",");
        for(String role:roleArray){
            GrantedAuthority authority = new SimpleGrantedAuthority(role.trim());
            authorities.add(authority);
        }
        UserDetails user = new User(userEntity.getUserName(),passwordEncoder.encode(userEntity.getPassword()),authorities);
        return user;
    }
}
