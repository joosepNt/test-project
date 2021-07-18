package com.example.testproject.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.hibernate.internal.util.StringHelper.isBlank;

public class ValidationUtil {

    public static boolean isBlankOrExceedingLimit(String value, int limit) {
        return isBlank(value) || value.length() > limit;
    }

    public static ResponseStatusException badRequest(String reason) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, reason);
    }
}
