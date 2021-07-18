package com.example.testproject.controller;

import com.example.testproject.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class ControllerBase {

    public User getLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
