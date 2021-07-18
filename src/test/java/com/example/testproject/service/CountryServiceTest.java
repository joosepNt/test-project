package com.example.testproject.service;

import com.example.testproject.TestBaseClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CountryServiceTest extends TestBaseClass {

    @Autowired
    private CountryService countryService;

    @Test
    void canFindAllCountries() {
        assertNotEmpty(countryService.findAllCountries());
    }

}
