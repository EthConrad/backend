package com.example.backend.services;

import com.example.backend.model.ChargePoint;
import com.example.backend.model.ChargingSession;
import com.example.backend.model.Vehicle;
import com.example.backend.repository.ChargingSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ChargeSessionService {

    private static final String SUCCESS = "Session ended successfully";

    private final ChargingSessionRepository repository;

    public List<ChargingSession> getChargingSessions(LocalDateTime fromDate, LocalDateTime toDate) {
        return Collections.emptyList();
//        return repository.findById();
    }

    public void startChargeSession(Vehicle vehicle, ChargePoint chargePoint) {
        ChargingSession session = new ChargingSession();
//        session.setChargePointId(chargePoint.getId());
//        repository.save()
    }

    public String endChargeSession() {

        try {
            System.out.println("Test");
         } catch (Exception e) {
            // If charge point terminates must set error message
            return e.getMessage();
        }
        return SUCCESS;
    }
}
