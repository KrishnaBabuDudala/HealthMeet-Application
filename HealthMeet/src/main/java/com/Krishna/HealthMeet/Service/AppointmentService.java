package com.Krishna.HealthMeet.Service;

import com.Krishna.HealthMeet.Entity.*;
import com.Krishna.HealthMeet.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;
    private final AdminDetailsRepository adminDetailsRepository;
    private final CustomerRepository customerRepository;
    public Appointment bookAppointment(Long doctorId, Long hospitalId, String speciality) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (!"patient".equalsIgnoreCase(customer.getRole())) {
            throw new RuntimeException("Access denied: Not a patient");
        }
        Long patientId = customer.getId();
        PatientDetails patient = patientDetailsRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        DoctorDetails doctor = doctorDetailsRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        AdminDetails hospital = adminDetailsRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));



        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setHospital(hospital);
        appointment.setSpeciality(speciality);
        appointment.setAppointmentDateTime(LocalDateTime.now());


        return appointmentRepository.save(appointment);
    }
    public List<Appointment> getAppointmentsByPatient() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (!"patient".equalsIgnoreCase(customer.getRole())) {
            throw new RuntimeException("Access denied: Not a patient");
        }
        Long patientId = customer.getId();

        List<Appointment> result=appointmentRepository.findByPatientId(patientId);

        return result;
    }
    public List<Appointment> getTodayAppointmentsByDoctor() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        Long doctorid = customer.getId();

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay(); // 00:00:00
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX); // 23:59:59.999999999

        return appointmentRepository.findTodayAppointmentsByDoctor(doctorid, startOfDay, endOfDay);
    }
}
