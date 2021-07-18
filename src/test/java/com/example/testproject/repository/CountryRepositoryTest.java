package com.example.testproject.repository;

import com.example.testproject.TestBaseClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CountryRepositoryTest extends TestBaseClass {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void canFindAllCountries() {
        assertNotEmpty(countryRepository.findAll());
    }

}
