package com.Krishna.HealthMeet.Controllers;

import com.Krishna.HealthMeet.Dto.PrescriptionRequest;
import com.Krishna.HealthMeet.Dto.PrescriptionResponse;
import com.Krishna.HealthMeet.Entity.Prescription;
import com.Krishna.HealthMeet.Service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    @PostMapping("/write")
    public String writePrescription(@RequestBody PrescriptionRequest request) {
        return prescriptionService.writePrescription(request);
    }
    @GetMapping("/{appointmentId}")
    public PrescriptionResponse getPrescription(@PathVariable Long appointmentId) {

        Prescription prescription = prescriptionService.getPrescriptionForPatient(appointmentId);

        return new PrescriptionResponse(
                prescription.getAppointment().getId(),
                prescription.getMedicineDetails(),
                prescription.getNotes()
        );
    }
}
