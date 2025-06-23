package com.Krishna.HealthMeet.Controllers;

import com.Krishna.HealthMeet.Dto.AppointmentRequest;
import com.Krishna.HealthMeet.Entity.Appointment;
import com.Krishna.HealthMeet.Service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    @PostMapping("/book")
    @PreAuthorize("hasAuthority('patient')")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody AppointmentRequest request) {
        Appointment appointment = appointmentService.bookAppointment(

                request.getDoctorId(),
                request.getHospitalId(),
                request.getSpeciality()
                );
        return ResponseEntity.ok(appointment);
    }
    @GetMapping("/getallappointments")
    @PreAuthorize("hasAuthority('patient')")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient() {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient());
    }
    @GetMapping("/todayappointments")
    public ResponseEntity<List<Appointment>> getTodayAppointmentsByDoctor() {
        return ResponseEntity.ok(appointmentService.getTodayAppointmentsByDoctor());
    }
}
