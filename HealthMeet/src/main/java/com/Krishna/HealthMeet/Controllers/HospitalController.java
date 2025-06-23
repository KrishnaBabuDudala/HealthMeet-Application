package com.Krishna.HealthMeet.Controllers;


import com.Krishna.HealthMeet.Entity.AdminDetails;
import com.Krishna.HealthMeet.repository.AdminDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final AdminDetailsRepository adminDetailsRepository;

    @GetMapping("/by-location")
    public ResponseEntity<List<AdminDetails>> getHospitalsByLocation(@RequestParam String location) {
        List<AdminDetails> hospitals = adminDetailsRepository.findByHospitalAddressIgnoreCaseContaining(location);
        return ResponseEntity.ok(hospitals);
    }


    @GetMapping("/by-speciality")
    public ResponseEntity<List<AdminDetails>> getHospitalsBySpeciality(@RequestParam String speciality) {
        List<AdminDetails> hospitals = adminDetailsRepository.findHospitalsBySpeciality(speciality);
        return ResponseEntity.ok(hospitals);
    }

    @GetMapping("/by-location-and-speciality")
    public ResponseEntity<List<String>> getByLocationAndSpeciality(
            @RequestParam String location,
            @RequestParam String speciality) {

        List<AdminDetails> hospitals = adminDetailsRepository.findByLocationAndSpeciality(location, speciality);
        System.out.println("Hospitals found: " + hospitals.size());
        for (AdminDetails hospital : hospitals) {
            System.out.println("Hospital: " + hospital.getHospitalName());
        }
        List<String> names = hospitals.stream()
                .map(AdminDetails::getHospitalName)
                .toList();

        return ResponseEntity.ok(names);
    }


}

