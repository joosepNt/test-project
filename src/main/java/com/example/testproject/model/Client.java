package com.example.testproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;

    @ManyToOne
    private Country country;

    @ManyToOne
    @JoinColumn(name = "managed_by", nullable = false)
    private User managedBy;

}
