package com.example.testproject.repository;

import com.example.testproject.model.Client;
import com.example.testproject.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Override
    <S extends Client> S save(S s);

    List<Client> findAllByManagedBy(User managedBy);

    Optional<Client> findByIdAndManagedBy(Long id, User managedBy);
}
