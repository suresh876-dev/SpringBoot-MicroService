package com.Suresh.SpringBoot_MicroService.service.Impl;


import com.Suresh.SpringBoot_MicroService.dto.UserDto;
import com.Suresh.SpringBoot_MicroService.entity.User;
import com.Suresh.SpringBoot_MicroService.exception.EmailAlreadyExistsException;
import com.Suresh.SpringBoot_MicroService.exception.ResourceNotFoundException;
import com.Suresh.SpringBoot_MicroService.repository.UserRepository;
import com.Suresh.SpringBoot_MicroService.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    EntityManager entityManager; // Heart of JPA , centrailised object heart of jpa java persistence api



    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userdto) {
        Optional<User> email = userRepository.findByEmail(userdto.getEmail());
        if(email.isPresent())
        {
            throw new EmailAlreadyExistsException("Email already Exists for user");
        }
        User savedUser = modelMapper.map(userdto,User.class);
        User user1 =  userRepository.save(savedUser);

        UserDto userDto = modelMapper.map(user1,UserDto.class);

      return userDto;

    }

    @Override
    public UserDto getUserById(Long id) {

        User optionalUser= userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("user","id",id));
            //User user = optionalUser.get();
            return modelMapper.map(optionalUser, UserDto.class);
        }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> getUsers = userRepository.findAll();
        return getUsers.stream().map((user)->modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateById(UserDto userDto) {
        User existignUser = userRepository.findById(userDto.getId()).orElseThrow(
                ()->new ResourceNotFoundException("user","id",userDto.getId()));

        existignUser.setFirstname(userDto.getFirstname());
        existignUser.setLastname(userDto.getLastname());
        existignUser.setEmail(userDto.getEmail());
        User updateUser = userRepository.save(existignUser);

        return modelMapper.map(updateUser,UserDto.class);
    }

    @Override
    public void deleteById(Long id) {
        User existignUser = userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("user","id",id));
        userRepository.deleteById(id);
    }



}
