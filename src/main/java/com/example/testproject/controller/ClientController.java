package com.example.testproject.controller;

import com.example.testproject.model.Client;
import com.example.testproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController extends ControllerBase {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "getUserClients")
    public List<Client> getUserClients() {
        return clientService.getUserClients(getLoggedInUser());
    }

    @GetMapping("findById")
    public Client findById(@RequestParam("id") Long id) {
        return clientService.findById(id, getLoggedInUser());
    }

    @PostMapping(value = "createOrUpdate")
    public Client add(@RequestBody Client client) {
        return clientService.createOrUpdate(client, getLoggedInUser());
    }
}
