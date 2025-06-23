package com.Krishna.HealthMeet.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrescriptionResponse {
    private Long appointmentId;
    private String medicineDetails;
    private String notes;
}
