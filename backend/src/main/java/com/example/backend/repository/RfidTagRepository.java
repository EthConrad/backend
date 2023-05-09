package com.example.backend.repository;

import com.example.backend.model.RFIDTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RfidTagRepository extends JpaRepository<RFIDTag, Long> {

    RFIDTag findByRegistrationNumber(String vehicleReg);
}
