package com.example.testproject.service;

import com.example.testproject.TestBaseClass;
import com.example.testproject.model.Client;
import com.example.testproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

import static com.example.testproject.TestConstants.*;
import static com.example.testproject.service.ClientService.*;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceTest extends TestBaseClass {

    @Autowired
    private ClientService clientService;

    @Test
    void userCanGetClients() {
        assertNotEmpty(clientService.getUserClients(testUser(TEST_USER_1)));
    }

    @Test
    void unAuthenticatedUserCanNotGetClients() {
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> clientService.getUserClients(null)
        );

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
        assertEquals(USER_IS_NOT_LOGGED_IN_MSG, exception.getReason());
    }

    @Test
    void userCanFindItsClients() {
        User requestingUser = testUser(TEST_USER_2);
        Client client = clientService.findById(TEST_CLIENT_1, requestingUser);

        assertNotNull(client);
        assertEquals(TEST_CLIENT_1, client.getId());
        assertEquals(TEST_USER_2, client.getManagedBy().getId());
    }

    @Test
    void userCanNotFindClientsManagedByOthers() {
        User requestingUser = testUser(TEST_USER_1);
        assertThrows(NoSuchElementException.class, () -> clientService.findById(TEST_CLIENT_1, requestingUser));
    }

    @Test
    void userCanAddNewClient() {
        User user = testUser(TEST_USER_1);
        Client clientToSave = client(null);
        assertNull(clientToSave.getId());
        assertNull(clientToSave.getManagedBy());

        Client savedClient = clientService.createOrUpdate(clientToSave, user);

        assertNotNull(savedClient.getId());
        assertNotNull(savedClient.getManagedBy());
    }

    @Test
    void userCanEditItsClient() {
        User user = testUser(TEST_USER_2);
        Client clientToEdit = clientService.findById(TEST_CLIENT_1, user);
        String initialClientUsername = clientToEdit.getUsername();
        assertNotNull(clientToEdit);
        assertNotNull(initialClientUsername);

        clientToEdit.setUsername("New username");
        Client updatedClient = clientService.createOrUpdate(clientToEdit, user);

        assertEquals(TEST_CLIENT_1, updatedClient.getId());
        assertEquals("New username", updatedClient.getUsername());
        assertNotEquals(initialClientUsername, updatedClient.getUsername());
    }

    @Test
    void userCanNotEditClientsManagedByOthers() {
        Client user2Client = clientService.findById(TEST_CLIENT_1, testUser(TEST_USER_2));
        assertEquals(TEST_USER_2, user2Client.getManagedBy().getId());

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> clientService.createOrUpdate(user2Client, testUser(TEST_USER_1))
        );
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
        assertEquals(NOT_AUTHORIZED_TO_EDIT_MSG, exception.getReason());
    }

    @Test
    void userCanNotSaveInvalidClient() {
        User user = testUser(TEST_USER_1);
        Client client;

        client = client(user);
        client.setFirstName("");
        exceptBadRequestWithMessageWhenSavingInvalidClient(client, user, format(INVALID_FIRST_NAME_MSG, DB_FIELD_SIZE_255));

        client = client(user);
        client.setLastName("");
        exceptBadRequestWithMessageWhenSavingInvalidClient(client, user, format(INVALID_LAST_NAME_MSG, DB_FIELD_SIZE_255));

        client = client(user);
        client.setUsername("");
        exceptBadRequestWithMessageWhenSavingInvalidClient(client, user, format(INVALID_USERNAME_MSG, DB_FIELD_SIZE_255));

        client = client(user);
        client.setAddress("");
        exceptBadRequestWithMessageWhenSavingInvalidClient(client, user, format(INVALID_ADDRESS_MSG, DB_FIELD_SIZE_500));

        client = client(user);
        client.setEmail("NotContainingAtSign");
        exceptBadRequestWithMessageWhenSavingInvalidClient(client, user, format(INVALID_EMAIL_MSG, DB_FIELD_SIZE_320));

        client = client(user);
        client.setCountry(null);
        exceptBadRequestWithMessageWhenSavingInvalidClient(client, user, MISSING_COUNTRY_MSG);

        client = client(null);
        exceptBadRequestWithMessageWhenSavingInvalidClient(client, null, MISSING_MANAGER_MSG);
    }

    private void exceptBadRequestWithMessageWhenSavingInvalidClient(Client client, User user, String message) {
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> clientService.createOrUpdate(client, user)
        );
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals(message, exception.getReason());
    }

    private Client client(User managedBy) {
        return new Client(
                null,
                "FirstName",
                "LastName",
                "Username",
                "email@mail",
                "address",
                testCountry(TEST_COUNTRY_1),
                managedBy
        );
    }

}
