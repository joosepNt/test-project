package com.example.testproject.controller;

import com.example.testproject.model.Country;
import com.example.testproject.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("findAll")
    public List<Country> findAllCountries() {
        return countryService.findAllCountries();
    }

}
