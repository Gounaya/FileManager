package com.rizomm.filemanager.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = {AuthRestAPIs.class})
@RunWith(SpringRunner.class)
public class AuthRestAPIsTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void authenticateUserReturnToken() throws Exception {
        /*
        LoginForm user = new LoginForm("Oussama", "123456789");

        mvc.perform(post("/api/auth/signin")
                .contentType("applicavtion/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn();
        */
    }


}
