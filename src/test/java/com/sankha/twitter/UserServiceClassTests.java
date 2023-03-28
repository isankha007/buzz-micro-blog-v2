package com.sankha.twitter;


import com.sankha.twitter.user.UserRepository;
import com.sankha.twitter.user.UserService;
import com.sankha.twitter.user.dto.CreateUserRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceClassTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Autowired
    private UserService service;


    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void Setup(){
        service=new UserService();
    }

    @Test
   // @DirtiesContext
    @Rollback(true)
    public void createUserTest() throws Exception {
        CreateUserRequestDto request=new CreateUserRequestDto();
        request.setUsername("abcddfeef");
        request.setEmail("asssdds@gmail.com");
        request.setPassword("SnIper@3213");
        //service.createNewUser(request);
        Assertions.assertThrows(Exception.class, () -> {
            service.createNewUser(request);
        });
    }
    @WithMockUser(username = "Sankhadeep2" ,password = "SnIper@3213")
    @Test
    void getList() throws Exception {
        MvcResult result=mockMvc.
                perform(get("/tweets/user/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(200, result.getResponse().getStatus());

    }

    @AfterEach
    void tearDown() {

    }
}
