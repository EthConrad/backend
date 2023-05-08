package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="VEHICLE")
public class Vehicle {

    @Id
    private long id;
    private String name;
    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;
}
