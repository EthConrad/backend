package com.example.backend.services;

import com.example.backend.model.RFIDTag;
import com.example.backend.repository.RfidTagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RfidTagService {

    private final RfidTagRepository repository;

    public RFIDTag getRfidTagByRegNumber(String regNumber) {
        RFIDTag tag = repository.findByRegistrationNumber(regNumber);

        if (tag == null) {
            throw new IllegalArgumentException("No RFID Tag exists for this vehicle registration number");
        }
        return tag;
    }

}
