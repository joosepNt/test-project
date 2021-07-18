package com.example.testproject;

import com.example.testproject.model.Country;
import com.example.testproject.model.User;
import com.example.testproject.repository.CountryRepository;
import com.example.testproject.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
public abstract class TestBaseClass {

    protected static final ObjectWriter objectWriter = new ObjectMapper().writer();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CountryRepository countryRepository;

    protected User testUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        assertNotNull(user);
        return user;
    }

    protected Country testCountry(Long id) {
        Country country = countryRepository.findById(id).orElseThrow();
        assertNotNull(country);
        return country;
    }

    protected void assertNotEmpty(List<?> list) {
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

}
