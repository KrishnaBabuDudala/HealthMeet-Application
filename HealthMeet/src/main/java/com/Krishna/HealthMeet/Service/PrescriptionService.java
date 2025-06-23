package com.Krishna.HealthMeet.Service;

import com.Krishna.HealthMeet.Dto.PrescriptionRequest;
import com.Krishna.HealthMeet.Entity.Appointment;
import com.Krishna.HealthMeet.Entity.Customer;
import com.Krishna.HealthMeet.Entity.Prescription;
import com.Krishna.HealthMeet.repository.AppointmentRepository;
import com.Krishna.HealthMeet.repository.CustomerRepository;
import com.Krishna.HealthMeet.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final AppointmentRepository appointmentRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final CustomerRepository customerRepository;
    public String writePrescription(PrescriptionRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Long doctorId = customer.getId();

        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (!appointment.getDoctor().getDoctorid().equals(doctorId)) {
            throw new RuntimeException("Unauthorized: This doctor is not assigned to the appointment.");
        }



        if (prescriptionRepository.existsByAppointmentId(request.getAppointmentId())) {
            throw new RuntimeException("Prescription already written for this appointment.");
        }

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setMedicineDetails(request.getMedicineDetails());
        prescription.setNotes(request.getNotes());

        prescriptionRepository.save(prescription);

        return "Prescription saved successfully.";
    }

    public Prescription getPrescriptionForPatient(Long appointmentId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Long patientId = customer.getId();
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));


        if (!appointment.getPatient().getPatient_Id().equals(patientId)) {
            throw new RuntimeException("Unauthorized: This patient does not own the appointment.");
        }

        return prescriptionRepository.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new RuntimeException("Prescription not found for this appointment."));
    }
}
