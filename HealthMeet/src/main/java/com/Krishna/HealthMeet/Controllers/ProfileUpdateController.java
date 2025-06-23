package com.Krishna.HealthMeet.Controllers;

import com.Krishna.HealthMeet.Dto.AdminDetailsDto;
import com.Krishna.HealthMeet.Entity.AdminDetails;
import com.Krishna.HealthMeet.Entity.DoctorDetails;
import com.Krishna.HealthMeet.Entity.PatientDetails;
import com.Krishna.HealthMeet.Service.ProfileUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileUpdateController {
    private final ProfileUpdateService profileUpdateService;



    @PostMapping("/update/patient")

    public ResponseEntity<String> updatePatient(@RequestBody PatientDetails patientDetails) {

        String result = profileUpdateService.updatePatientProfile(patientDetails);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update/admin")

    public ResponseEntity<String> updateAdmin(@RequestBody AdminDetailsDto adminDetailsDto) {
        String result = profileUpdateService.updateAdminProfile(adminDetailsDto);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/update/doctor")

    public ResponseEntity<String> updateDoctor(@RequestBody DoctorDetails doctorDetails) {
        String result = profileUpdateService.updateDoctorProfile(doctorDetails);
        return ResponseEntity.ok(result);
    }
}
