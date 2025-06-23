package com.Krishna.HealthMeet.repository;

import com.Krishna.HealthMeet.Entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    boolean existsByAppointmentId(Long appointmentId);
    Optional<Prescription> findByAppointmentId(Long appointmentId);
}
