package com.example.testproject.controller;

import com.example.testproject.TestBaseClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.testproject.TestConstants.TEST_USER_PASSWORD;
import static com.example.testproject.TestConstants.TEST_USER_USERNAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class HtmlControllerTest extends TestBaseClass {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = TEST_USER_USERNAME, password = TEST_USER_PASSWORD)
    void canGetIndexPage() throws Exception {
        performGetAndExceptOkViewToBe("/", "index.html");
    }

    @Test
    @WithMockUser(username = TEST_USER_USERNAME, password = TEST_USER_PASSWORD)
    void canGetEditPage() throws Exception {
        performGetAndExceptOkViewToBe("/edit", "client.html");
    }

    @Test
    @WithMockUser(username = TEST_USER_USERNAME, password = TEST_USER_PASSWORD)
    void canGetAddPage() throws Exception {
        performGetAndExceptOkViewToBe("/add", "client.html");
    }

    @Test
    void unAuthenticatedUserCanNotGetViews() throws Exception {
        performGetAndExceptUnauthorized("/");
        performGetAndExceptUnauthorized("/edit");
        performGetAndExceptUnauthorized("/add");
    }

    private void performGetAndExceptOkViewToBe(String url, String viewName) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));
    }

    private void performGetAndExceptUnauthorized(String url) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isUnauthorized());
    }

}

