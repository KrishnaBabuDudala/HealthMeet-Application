package com.Krishna.HealthMeet.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescriptionRequest {
    private Long appointmentId;
    private String medicineDetails;
    private String notes;
}
