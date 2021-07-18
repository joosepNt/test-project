package com.example.testproject.controller;

import com.example.testproject.TestBaseClass;
import com.example.testproject.model.Country;
import com.example.testproject.service.CountryService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerTest extends TestBaseClass {

    @MockBean
    private CountryService countryService;

    @Autowired
    private MockMvc mockMvc;

    private final ArrayList<Country> mockCountries = Lists.newArrayList(
            new Country(1L, "Estonia"),
            new Country(2L, "Latvia"),
            new Country(3L, "Lithuania"));

    @Test
    void canGetAllCountries() throws Exception {
        doReturn(mockCountries).when(countryService).findAllCountries();

        mockMvc.perform(get("/country/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectWriter.writeValueAsString(mockCountries)));
    }

}
