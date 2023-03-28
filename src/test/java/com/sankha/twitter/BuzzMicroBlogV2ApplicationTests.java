package com.sankha.twitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BuzzMicroBlogV2ApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;


	@BeforeEach
	void setUp() {
		mockMvc= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}


	@Test
	void contextLoads() {
	}

	@WithMockUser("/sankha")
	@Test
	void getList() throws Exception {
		MvcResult result=mockMvc.
						perform(get("/tweets/user/list").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());

	}

}
