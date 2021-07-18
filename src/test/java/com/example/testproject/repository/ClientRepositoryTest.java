package com.example.testproject.repository;

import com.example.testproject.TestBaseClass;
import com.example.testproject.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static com.example.testproject.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientRepositoryTest extends TestBaseClass {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void canFindAllByManagedByUser() {
        List<Client> userClients = clientRepository.findAllByManagedBy(testUser(TEST_USER_1));
        assertNotEmpty(userClients);
        userClients.forEach(client -> {
            assertEquals(TEST_USER_1, client.getManagedBy().getId());
        });
    }

    @Test
    void canFindByIdAndManagedByUser() {
        Optional<Client> client = clientRepository.findByIdAndManagedBy(TEST_CLIENT_1, testUser(TEST_USER_2));
        assertTrue(client.isPresent());
    }

    @Test
    void userCanNotGetClientManagedByOtherUser() {
        Optional<Client> client = clientRepository.findByIdAndManagedBy(TEST_CLIENT_1, testUser(TEST_USER_1));
        assertFalse(client.isPresent());
    }

    @Test
    void canSaveNewClient() {
        Client client = new Client(null, "Test", "Client", "TestClientX",
                "test@client.mail", "Test address", testCountry(TEST_COUNTRY_1), testUser(TEST_USER_1));
        Client savedClient = clientRepository.save(client);

        assertNotNull(savedClient);
    }

    @Test
    void canEditClient() {
        Client client = clientRepository.findById(TEST_CLIENT_1).orElseThrow();
        String initialUsername = client.getUsername();

        client.setUsername("New username");
        Client updatedClient = clientRepository.save(client);

        assertEquals(TEST_CLIENT_1, updatedClient.getId());
        assertEquals("New username", updatedClient.getUsername());
        assertNotEquals("New username", initialUsername);
    }

}
