package com.Krishna.HealthMeet.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor

@Table(name = "AdminDetails")
public class AdminDetails {
    @Id

    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "hospital_phone_no")
    private String hospitalPhoneNo;

    @Column(name = "hospital_address")
    private String hospitalAddress;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Speciality> specialities = new ArrayList<>();


}
