package com.Krishna.HealthMeet.repository;

import com.Krishna.HealthMeet.Entity.DoctorDetails;
import com.Krishna.HealthMeet.Entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Long> {
}
