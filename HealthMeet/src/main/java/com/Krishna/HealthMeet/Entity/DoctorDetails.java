package com.Krishna.HealthMeet.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@NoArgsConstructor
@Table(name="Doctor_Details")
public class DoctorDetails {
    @Id
    @Column(name="doctor_id")
    Long doctorid;
    String fullname;
    String phnno;
    String speciality;
}
