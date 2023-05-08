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

    @JoinColumn(name = "chargePoint_id", nullable = false)
    @OneToOne
    private ChargePoint chargePointId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String initialMeterValue;
    private String finalMeterValue;
    private String errorMessage;
}
