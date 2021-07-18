package com.example.testproject.repository;

import com.example.testproject.model.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long> {

    List<Country> findAll();

}
