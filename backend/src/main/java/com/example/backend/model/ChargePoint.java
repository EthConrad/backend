package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Only one customer can be using charge point, but the point has multiple connectors
 */

@Getter
@Setter
@Entity
@Table(name="CHARGE_POINT")
public class ChargePoint {
    @Id
    private long id;
    @JoinColumn(name = "RFID_TAG_ID")
    @OneToOne
    private RFIDTag rfidTag;
    @Column(name = "UNIQUE_SERIAL_NUMBER")
    private String uniqueSerialNumber;
    // A charge point can have 1 or more connectorNumbers. I have assumed these are unique, hence, using a set
    // There will be multiple DB entries for this, as each one may have different connectorNumber
    @Column(name = "CONNECTOR_NUMBER")
    private String connectorNumber;
}
