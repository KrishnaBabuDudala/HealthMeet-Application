package com.Krishna.HealthMeet.Dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class AppointmentRequest {

    private Long doctorId;
    private Long hospitalId;
    private String speciality;


}

