package com.example.testproject.repository;

import com.example.testproject.TestBaseClass;
import com.example.testproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.example.testproject.TestConstants.TEST_USER_1;
import static com.example.testproject.TestConstants.TEST_USER_1_USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTest extends TestBaseClass {

    @Autowired
    private UserRepository userRepository;

    @Test
    void canFindByUsername() {
        User user = userRepository.findByUsername(TEST_USER_1_USERNAME);

        assertNotNull(user);
        assertEquals(TEST_USER_1, user.getId());
        assertEquals(TEST_USER_1_USERNAME, user.getUsername());
    }

}
