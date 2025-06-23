package com.Krishna.HealthMeet.repository;

import com.Krishna.HealthMeet.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.patient.patient_Id = :patientId")
    List<Appointment> findByPatientId(@Param("patientId") Long patientId);

    // Appointments for doctor on current day
    @Query("SELECT a FROM Appointment a WHERE a.doctor.doctorid = :doctorid AND a.appointmentDateTime BETWEEN :start AND :end")
    List<Appointment> findTodayAppointmentsByDoctor(
            @Param("doctorid") Long doctorid,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
