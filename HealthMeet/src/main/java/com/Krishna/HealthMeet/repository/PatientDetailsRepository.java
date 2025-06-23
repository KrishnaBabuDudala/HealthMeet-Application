package com.Krishna.HealthMeet.repository;

import com.Krishna.HealthMeet.Entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Long> {

}
