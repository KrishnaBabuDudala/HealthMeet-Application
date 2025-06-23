package com.Krishna.HealthMeet.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientDetails patient;
    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private AdminDetails hospital;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorDetails doctor;




    private String speciality;

    @Column(name = "appointment_datetime", nullable = false)
    private LocalDateTime appointmentDateTime;


}

