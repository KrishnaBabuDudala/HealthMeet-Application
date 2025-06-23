package com.Krishna.HealthMeet.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AdminDetailsDto {
    private String hospitalName;
    private String hospitalPhoneNo;
    private String hospitalAddress;
    private List<String> specialities;
}
