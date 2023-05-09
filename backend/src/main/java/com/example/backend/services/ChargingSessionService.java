package com.example.backend.services;

import com.example.backend.model.ChargePoint;
import com.example.backend.model.ChargingSession;
import com.example.backend.model.RFIDTag;
import com.example.backend.repository.ChargingSessionRepository;
import com.example.backend.utils.DateTimeConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ChargingSessionService {

    private final ChargingSessionRepository chargingSessionRepository;
    private final ChargePointService chargePointService;

    private final RfidTagService rfidTagService;

    // Unit tests to be written here must check the following scenarios:
    // 1 - Happy path, fromDate is before toDate.
    // Would need at least 3 ChargingSessions one before the dates, one in the middle and one after
    // 2 - FromDate is after toDate, should throw Exception
    // We do not need to check invalid dates because they are unit tested in DateTimeConverter
    // 3 - FromDate is before toDate but no sessions are returned. Ensure empty list is returned

    public List<ChargingSession> getChargingSessions(String fromDate, String toDate) {
        LocalDateTime convertedStartDate = DateTimeConverter.convertDateTime(fromDate);
        LocalDateTime convertedEndDate = DateTimeConverter.convertDateTime(toDate);

        if (convertedStartDate.isAfter(convertedEndDate)) {
            throw new IllegalArgumentException("End Date must be after Start Date");
        }

        return chargingSessionRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(convertedStartDate, convertedEndDate);
    }

    public void startChargeSession(String uniqueSerialNumber, String connector, String vehicleReg) {

        ChargePoint chargePoint = chargePointService.getChargePoint(uniqueSerialNumber, connector);

        RFIDTag rfidTag = rfidTagService.getRfidTagByRegNumber(vehicleReg);

        ChargingSession session = chargingSessionRepository.findFirstByChargePointIdOrderByEndDateDesc(chargePoint.getId());

        if (session != null && session.getEndDate() == null) {
            throw new IllegalStateException("The previous Charging Session has not ended yet");
        }

        ChargingSession newSession = new ChargingSession();

        // If no session has ever existed, start meter from 0 else grab the previous final value
        if (session == null) {
            newSession.setInitialMeterValue("0");
        } else {
            newSession.setInitialMeterValue(session.getFinalMeterValue());
        }

        newSession.setStartDate(LocalDateTime.now());
        chargePoint.setRfidTag(rfidTag);
        newSession.setChargePoint(chargePoint);
        chargePointService.updateChargePoint(chargePoint);
        chargingSessionRepository.save(newSession);
    }

    public void endChargeSession(String uniqueSerialNumber, String connector, String finalMeterValue) {

        ChargePoint chargePoint = chargePointService.getChargePoint(uniqueSerialNumber, connector);

        ChargingSession session = chargingSessionRepository.findFirstByChargePointIdOrderByEndDateDesc(chargePoint.getId());

        if (session == null) {
            throw new IllegalStateException("Charging Session is currently not in progress for the Charge Point details provided");
        }

        if (session.getEndDate() != null) {
            throw new IllegalStateException("Charging Session has already ended");
        }

        String errorMessage = null;
        try {
            additionalOperations();
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        if (errorMessage == null) {
            session.setEndDate(LocalDateTime.now());
            session.setFinalMeterValue(finalMeterValue);
        } else {
            // Depending on desired functionality here, we may want to still set endDate and final meter value
            // However, if the chargepoint needs investigating, it may be best to leave it open in an invalid state
            session.setErrorMessage(errorMessage);
        }

        // Remove relationship between chargepoint and vehicle so that it can be swapped to another chargepoint
        chargePoint.setRfidTag(null);
        chargePointService.updateChargePoint(chargePoint);
        chargingSessionRepository.save(session);
    }

    // Stub method that would potentially talk to any 3rd party libraries. Throws Exception if there is an error ending session
    private void additionalOperations() throws Exception {

    }
}
