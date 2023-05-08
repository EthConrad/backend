package com.example.backend.controllers;

import com.example.backend.model.ChargingSession;
import com.example.backend.services.ChargingSessionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chargeSessions")
public class ChargeSessionController {

    private final ChargingSessionService chargingSessionService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ChargingSession> getChargingSessions(@RequestParam String fromDate,
                                                                  @RequestParam String toDate) {
        return chargingSessionService.getChargingSessions(fromDate, toDate);
    }

    @PostMapping("/start")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public void startChargeSession(@RequestParam String uniqueSerialNumber, @RequestParam String connector) {
        chargingSessionService.startChargeSession(uniqueSerialNumber, connector);
    }

    @PostMapping("/end")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public void endChargeSession(@RequestParam String uniqueSerialNumber,
                                 @RequestParam String connector,
                                 @RequestParam String finalMeterValue) {
        chargingSessionService.endChargeSession(uniqueSerialNumber, connector, finalMeterValue);
    }
}
