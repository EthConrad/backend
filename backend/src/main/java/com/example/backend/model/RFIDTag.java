package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="RFID_TAG")
public class RFIDTag {

    @Id
    private long id;
    @JoinColumn(name = "VEHICLE_ID")
    @OneToOne
    private Vehicle vehicle;
}
