package com.example.testproject.controller;

import com.example.testproject.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {

    @GetMapping("clients")
    public List<Client> getUserClients() {
        return new ArrayList<>();
    }

}
