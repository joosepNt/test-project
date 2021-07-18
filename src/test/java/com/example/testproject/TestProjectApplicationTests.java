package com.example.testproject;

import com.example.testproject.controller.ClientController;
import com.example.testproject.controller.CountryController;
import com.example.testproject.controller.HtmlController;
import com.example.testproject.repository.ClientRepository;
import com.example.testproject.repository.CountryRepository;
import com.example.testproject.repository.UserRepository;
import com.example.testproject.service.ClientService;
import com.example.testproject.service.CountryService;
import com.example.testproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class TestProjectApplicationTests {

	@Autowired private ClientController clientController;
	@Autowired private CountryController countryController;
	@Autowired private HtmlController htmlController;

	@Autowired private ClientRepository clientRepository;
	@Autowired private CountryRepository countryRepository;
	@Autowired private UserRepository userRepository;

	@Autowired private ClientService clientService;
	@Autowired private CountryService countryService;
	@Autowired private UserService userService;

    @Test
    void contextLoads() {
    	assertNotNull(clientController);
    	assertNotNull(countryController);
    	assertNotNull(htmlController);

    	assertNotNull(clientRepository);
    	assertNotNull(countryRepository);
    	assertNotNull(userRepository);

    	assertNotNull(clientService);
    	assertNotNull(countryService);
    	assertNotNull(userService);
    }

}
