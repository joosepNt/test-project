package com.example.testproject.service;

import com.example.testproject.TestBaseClass;
import com.example.testproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.example.testproject.TestConstants.TEST_USER_1;
import static com.example.testproject.TestConstants.TEST_USER_1_USERNAME;
import static com.example.testproject.service.UserService.USERNAME_NOT_FOUND_MESSAGE;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest extends TestBaseClass {

    @Autowired
    private UserService userService;

    @Test
    void canLoadUserByUsername() {
        User user = userService.loadUserByUsername(TEST_USER_1_USERNAME);

        assertNotNull(user);
        assertEquals(TEST_USER_1, user.getId());
        assertEquals(TEST_USER_1_USERNAME, user.getUsername());
    }

    @Test
    void throwUsernameNotFoundExceptionWhenUserNotFound() {
        String username = "NotExistingUser";
        try {
            assertNull(userService.loadUserByUsername(username));
        } catch (UsernameNotFoundException e) {
            assertEquals(format(USERNAME_NOT_FOUND_MESSAGE, username), e.getMessage());
        }
    }

    @Test
    void passwordEncoderIsBCryptPasswordEncoder() {
        assertEquals(BCryptPasswordEncoder.class, userService.passwordEncoder().getClass());
    }

}
