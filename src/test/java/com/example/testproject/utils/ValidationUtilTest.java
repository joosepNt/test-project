package com.example.testproject.utils;

import com.example.testproject.TestBaseClass;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest extends TestBaseClass {

    @Test
    void isBlankOrExceedingLimit() {
        assertTrue(ValidationUtil.isBlankOrExceedingLimit("", 0));
        assertTrue(ValidationUtil.isBlankOrExceedingLimit(" ", 0));
        assertTrue(ValidationUtil.isBlankOrExceedingLimit("\t", 0));
        assertTrue(ValidationUtil.isBlankOrExceedingLimit("\r", 0));
        assertTrue(ValidationUtil.isBlankOrExceedingLimit("\n", 0));

        assertTrue(ValidationUtil.isBlankOrExceedingLimit("a", 0));
        assertTrue(ValidationUtil.isBlankOrExceedingLimit("String too long", 5));

        assertFalse(ValidationUtil.isBlankOrExceedingLimit("a", 1));
        assertFalse(ValidationUtil.isBlankOrExceedingLimit("valid", 10));
    }

    @Test
    void canComposeBadRequest() {
        ResponseStatusException badRequest= ValidationUtil.badRequest("Bad request message");

        assertEquals(HttpStatus.BAD_REQUEST, badRequest.getStatus());
        assertEquals("Bad request message", badRequest.getReason());
    }

}
