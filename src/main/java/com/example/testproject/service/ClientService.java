package com.example.testproject.service;

import com.example.testproject.model.Client;
import com.example.testproject.model.User;
import com.example.testproject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.example.testproject.utils.ValidationUtil.badRequest;
import static com.example.testproject.utils.ValidationUtil.isBlankOrExceedingLimit;
import static java.lang.String.format;

@Service
public class ClientService {

    public static final String USER_IS_NOT_LOGGED_IN_MSG = "User is not logged in";
    public static final String NOT_AUTHORIZED_TO_EDIT_MSG = "You are not authorized to edit this client";
    public static final String INVALID_FIRST_NAME_MSG = "Required field 'firstName' is missing or exceeding the limit of %s characters";
    public static final String INVALID_LAST_NAME_MSG = "Required field 'lastName' is missing or exceeding the limit of %s characters";
    public static final String INVALID_USERNAME_MSG = "Required field 'username' is missing or exceeding the limit of %s characters";
    public static final String INVALID_ADDRESS_MSG = "Required field 'address' is missing or exceeding the limit of %s characters";
    public static final String INVALID_EMAIL_MSG = "Field 'email' is exceeding the limit of %s characters or missing @ sign";
    public static final String MISSING_COUNTRY_MSG = "Required field 'country' is missing";
    public static final String MISSING_MANAGER_MSG = "Client is missing manager user";
    static final int DB_FIELD_SIZE_255 = 255;
    static final int DB_FIELD_SIZE_320 = 320;
    static final int DB_FIELD_SIZE_500 = 500;

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getUserClients(User requestingUser) {
        if (requestingUser == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_IS_NOT_LOGGED_IN_MSG);
        return clientRepository.findAllByManagedBy(requestingUser);
    }

    public Client createOrUpdate(Client client, User savingUser) {
        canEdit(client, savingUser);
        client.setManagedBy(savingUser);
        validateRequiredFields(client);
        return clientRepository.save(client);
    }

    public Client findById(Long id, User requestingUser) {
        Optional<Client> optionalClient = clientRepository.findByIdAndManagedBy(id, requestingUser);
        return optionalClient.orElseThrow();
    }

    private void canEdit(Client client, User savingUser) {
        if (client.getManagedBy() != null && !client.getManagedBy().getId().equals(savingUser.getId()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, NOT_AUTHORIZED_TO_EDIT_MSG);
    }

    private void validateRequiredFields(Client client) {
        if (isBlankOrExceedingLimit(client.getFirstName(), DB_FIELD_SIZE_255))
            throw badRequest(format(INVALID_FIRST_NAME_MSG, DB_FIELD_SIZE_255));
        if (isBlankOrExceedingLimit(client.getLastName(), DB_FIELD_SIZE_255))
            throw badRequest(format(INVALID_LAST_NAME_MSG, DB_FIELD_SIZE_255));
        if (isBlankOrExceedingLimit(client.getUsername(), DB_FIELD_SIZE_255))
            throw badRequest(format(INVALID_USERNAME_MSG, DB_FIELD_SIZE_255));
        if (isBlankOrExceedingLimit(client.getAddress(), DB_FIELD_SIZE_500))
            throw badRequest(format(INVALID_ADDRESS_MSG, DB_FIELD_SIZE_500));
        if (!client.getEmail().isBlank() && (client.getEmail().length() > DB_FIELD_SIZE_320 || !client.getEmail().contains("@")))
            throw badRequest(format(INVALID_EMAIL_MSG, DB_FIELD_SIZE_320));
        if (client.getCountry() == null)
            throw badRequest(MISSING_COUNTRY_MSG);
        if (client.getManagedBy() == null)
            throw badRequest(MISSING_MANAGER_MSG);
    }
}
