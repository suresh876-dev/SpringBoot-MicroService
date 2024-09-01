package com.Suresh.SpringBoot_MicroService.Controller;


import com.Suresh.SpringBoot_MicroService.controller.UserController;
import com.Suresh.SpringBoot_MicroService.dto.UserDto;
import com.Suresh.SpringBoot_MicroService.security.JwtUtils;
import com.Suresh.SpringBoot_MicroService.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@Import(JwtUtils.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc; // Used to send Rest call request

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    UserService userService;

    /*@MockBean
    ObjectMapper objectMapper;*/

    //@Test
    //@Ignore
    @WithMockUser(username = "bipin", roles = {"USER"})
    public void testCreateUser() throws  Exception {
        UserDto userDto = new UserDto(23L,"mallika","juvva","k@gmail.com");
                when(userService.createUser(userDto)).thenReturn(userDto);
        MvcResult mvcResult =  this.mockMvc.perform(post("http://localhost:8080/user/createUser").contentType("application/json")
                    //    .content(objectMapper.writeValueAsString(userDto))
                        ).andExpect(status().isCreated())
                        .andReturn();
        String respone = mvcResult.getResponse().getContentAsString();
        assertTrue(respone.contains("mallika"));

    }

    @Test
    @WithMockUser(username = "bipin", roles = {"USER"})
    public void testGetAllUsers() throws Exception {
        List<UserDto> user = new ArrayList<>();
        user.add( new UserDto(23L,"mallika","juvva","k@gmail.com"));
        user.add(new UserDto(89L,"radhika","krsf","kla@gmail.com"));
        when(userService.getAllUsers()).thenReturn(user);
        MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8080/user/getAllUsers"))
                .andExpect(status().isOk())
                .andReturn();
        String respone = mvcResult.getResponse().getContentAsString();
        assertTrue(respone.contains("mallika"));
    }
}
