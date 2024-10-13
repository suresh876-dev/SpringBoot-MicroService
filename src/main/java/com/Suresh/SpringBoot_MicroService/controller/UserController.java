package com.Suresh.SpringBoot_MicroService.controller;

import com.Suresh.SpringBoot_MicroService.dto.UserDto;
import com.Suresh.SpringBoot_MicroService.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private UserService userService;


    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
       UserDto savedUser = userService.createUser(userDto);
       //check role of jwt token for every user
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/findbyId/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id)
    {
        UserDto getUser = userService.getUserById(id);
        return new ResponseEntity<UserDto>(getUser,HttpStatus.OK);

    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> users = userService.getAllUsers();

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto)
    {
        userDto.setId(id);
        UserDto  updateuser = userService.updateById(userDto);

        return  new ResponseEntity<UserDto>(updateuser,HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id)
    {
        userService.deleteById(id);

        return new ResponseEntity<>("user delted succsfully",HttpStatus.OK);
    }

   /* @CircuitBreaker(name="TOKEN-VALIDATION-SERVICE",fallbackMethod="fallbackForIsTokenValid")
    public Boolean isTokenValid(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<Boolean> response =restTemplate.exchange("https://api.restful-api.dev/objects", HttpMethod.GET,entity,Boolean.class);
        return response.getBody();

    }

    public boolean fallbackForIsTokenValid(Exception ex){
        System.out.println("called service failed-excepiotn"+ex);
        System.out.println("called service failed-excepiotn"+ex);
        return false;
    }*/
}
