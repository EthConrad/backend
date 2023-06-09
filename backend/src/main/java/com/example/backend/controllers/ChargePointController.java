package com.example.backend.controllers;

import com.example.backend.dto.ChargeDTO;
import com.example.backend.services.ChargePointService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/chargePoints")
public class ChargePointController {

    private final ChargePointService chargePointService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addConnector(@RequestBody ChargeDTO dto) {
        chargePointService.addConnector(dto.getUniqueSerialNumber(), dto.getConnector());
    }
}
