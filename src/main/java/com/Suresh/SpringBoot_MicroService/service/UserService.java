package com.Suresh.SpringBoot_MicroService.service;

import com.Suresh.SpringBoot_MicroService.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateById(UserDto userDto);

    void deleteById(Long id);

}
