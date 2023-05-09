package com.example.backend.repository;

import com.example.backend.model.ChargingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChargingSessionRepository extends JpaRepository<ChargingSession, Long> {

    List<ChargingSession> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDateTime startDate,
                                                                                    LocalDateTime endDate);

    ChargingSession findFirstByChargePointIdOrderByEndDateDesc(long chargePointId);
}
