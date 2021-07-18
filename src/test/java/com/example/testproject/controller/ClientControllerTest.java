package com.example.testproject.controller;

import com.example.testproject.TestBaseClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static java.lang.String.format;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest extends TestBaseClass {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void unAuthenticatedUserCanNotMakeRequests() throws Exception {
        performGetAndExceptUnauthorized("getUserClients");
        performGetAndExceptUnauthorized("findById");
        performGetAndExceptUnauthorized("createOrUpdate");
        performGetAndExceptUnauthorized("createOrUpdate");
    }

    private void performGetAndExceptUnauthorized(String url) throws Exception {
        mockMvc.perform(get(format("/client/%s", url)))
                .andExpect(status().isUnauthorized());
    }

}

