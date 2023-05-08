package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="CUSTOMER")
public class Customer {
    @Id
    private long id;
    private String name;
    @JoinColumn(name = "RFID_TAG_ID", nullable = false)
    @OneToOne
    private RFIDTag rfidTag;
}
