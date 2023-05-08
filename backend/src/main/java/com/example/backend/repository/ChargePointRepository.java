package com.example.backend.repository;

import com.example.backend.model.ChargePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargePointRepository extends JpaRepository<ChargePoint, Long> {

    ChargePoint findByUniqueSerialNumberAndConnectorNumber(String uniqueSerialNumber, String connectorNumber);
}
