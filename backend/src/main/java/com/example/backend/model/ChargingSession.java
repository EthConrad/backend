package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="CHARGING_SESSION")
public class ChargingSession {
    @Id
    private String id;

    @JoinColumn(name = "CHARGEPOINT_ID", nullable = false)
    @OneToOne
    private ChargePoint chargePoint;
    @Column(name = "STARTDATE")
    private LocalDateTime startDate;
    @Column(name = "ENDDATE")
    private LocalDateTime endDate;
    @Column(name = "INITIALMETER_VALUE")
    private String initialMeterValue;
    @Column(name = "FINALMETER_VALUE")
    private String finalMeterValue;
    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;
}
