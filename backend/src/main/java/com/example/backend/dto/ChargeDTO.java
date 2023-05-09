package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeDTO {

    private String uniqueSerialNumber;
    private String connector;
    private String vehicleReg;
    private String finalMeterValue;
}
