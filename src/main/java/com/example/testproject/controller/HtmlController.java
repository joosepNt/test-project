package com.example.testproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("edit")
    public String editClientView() {
        return "client.html";
    }

    @GetMapping("add")
    public String addClientView() {
        return "client.html";
    }

}
