package com.Krishna.HealthMeet.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@NoArgsConstructor
@Table(name = "PatientDetails")
public class PatientDetails {
    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_details_seq")
    //@SequenceGenerator(
            //name = "patient_details_seq",
            //sequenceName = "patient_details_seq",
            //allocationSize = 1)

    //private Long userId;
    @Id
    private Long patient_Id;



    private String fullName;
    private int age;
    private String gender;
    private int weight;
    private String phone;
    private String address;


}
