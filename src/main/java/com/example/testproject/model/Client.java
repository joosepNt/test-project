package com.example.testproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;

    @ManyToOne
    @JoinColumn(name = "country", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "managed_by", nullable = false)
    @JsonBackReference
    private User managedBy;

}
