package com.example.backend.controllers;

import com.example.backend.model.ChargingSession;
import com.example.backend.services.ChargeSessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class ChargeSessionController {

    private final ChargeSessionService chargeSessionService;

    @GetMapping
    public List<ChargingSession> getChargingSessions(@RequestParam LocalDateTime fromDate,
                                                     @RequestParam LocalDateTime toDate) {
        return chargeSessionService.getChargingSessions(fromDate, toDate);
    }
}
