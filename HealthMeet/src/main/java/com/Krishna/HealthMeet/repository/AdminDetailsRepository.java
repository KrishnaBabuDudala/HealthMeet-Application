package com.Krishna.HealthMeet.repository;

import com.Krishna.HealthMeet.Entity.AdminDetails;
import com.Krishna.HealthMeet.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminDetailsRepository extends JpaRepository<AdminDetails, Long> {
    List<AdminDetails> findByHospitalAddressIgnoreCaseContaining(String hospitalAddress);


    @Query("SELECT a FROM AdminDetails a JOIN a.specialities s WHERE LOWER(s.name) = LOWER(:speciality)")
    List<AdminDetails> findHospitalsBySpeciality(@Param("speciality") String speciality);


    @Query("SELECT a FROM AdminDetails a JOIN a.specialities s " +
            "WHERE LOWER(a.hospitalAddress) LIKE LOWER(CONCAT('%', :location, '%')) " +
            "AND LOWER(s.name) = LOWER(:speciality)")
    List<AdminDetails> findByLocationAndSpeciality(@Param("location") String location,
                                                   @Param("speciality") String speciality);




}
