package com.example.backend.services;

import com.example.backend.model.ChargePoint;
import com.example.backend.repository.ChargePointRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChargePointService {

    private final ChargePointRepository repository;

    public void addConnector(String uniqueSerialNumber, String connector) {
        ChargePoint chargePoint = repository.findByUniqueSerialNumberAndConnectorNumber(uniqueSerialNumber, connector);

        if (chargePoint != null) {
            throw new IllegalArgumentException("Charge point for connector number already exists");
        }

        chargePoint = new ChargePoint();
        chargePoint.setConnectorNumber(connector);
        chargePoint.setUniqueSerialNumber(uniqueSerialNumber);
        repository.save(chargePoint);
    }

    protected ChargePoint getChargePoint(String uniqueSerialNumber, String connector) {
        ChargePoint chargePoint = repository.findByUniqueSerialNumberAndConnectorNumber(uniqueSerialNumber, connector);

        if (chargePoint == null) {
            throw new IllegalArgumentException("Charge Point does not exist, hence, charge session cannot be started");
        }
        return chargePoint;
    }
}
