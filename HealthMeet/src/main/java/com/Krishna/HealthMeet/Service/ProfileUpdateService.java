package com.Krishna.HealthMeet.Service;

import com.Krishna.HealthMeet.Dto.AdminDetailsDto;
import com.Krishna.HealthMeet.Entity.*;
import com.Krishna.HealthMeet.repository.CustomerRepository;
import com.Krishna.HealthMeet.repository.DoctorDetailsRepository;
import com.Krishna.HealthMeet.repository.PatientDetailsRepository;
import com.Krishna.HealthMeet.repository.AdminDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileUpdateService {
    private final CustomerRepository customerRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    private final AdminDetailsRepository adminDetailsRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;



    public String updatePatientProfile(PatientDetails updatedData) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (!"patient".equalsIgnoreCase(customer.getRole())) {
            throw new RuntimeException("Access denied: Not a patient");
        }

        //PatientDetails patient = patientDetailsRepository.findByCustomerId(customer.getId())
                //.orElseThrow(() -> new RuntimeException("Patient profile not found"));

        // Update only relevant fields
        Long patient_Id = customer.getId();
        Optional<PatientDetails> existingPatientOpt = patientDetailsRepository.findById(patient_Id);

        PatientDetails patient;
        if (existingPatientOpt.isPresent()) {
            patient = existingPatientOpt.get();
            // update fields
            patient.setFullName(updatedData.getFullName());
            patient.setAge(updatedData.getAge());
            patient.setGender(updatedData.getGender());
            patient.setWeight(updatedData.getWeight());
            patient.setPhone(updatedData.getPhone());
            patient.setAddress(updatedData.getAddress());
        } else {
            patient = updatedData;
            patient.setPatient_Id(patient_Id); // set PK from Customer id
        }

        patientDetailsRepository.save(patient);

        return "Patient profile updated";
    }

    public String updateAdminProfile(AdminDetailsDto request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (!"admin".equalsIgnoreCase(customer.getRole())) {
            throw new RuntimeException("Access denied: Not an admin");
        }

        Long adminId = customer.getId();
        Optional<AdminDetails> existingAdminOpt = adminDetailsRepository.findById(adminId);

        AdminDetails admin;
        if (existingAdminOpt.isPresent()) {
            admin = existingAdminOpt.get();
            admin.setHospitalName(request.getHospitalName());
            admin.setHospitalPhoneNo(request.getHospitalPhoneNo());
            admin.setHospitalAddress(request.getHospitalAddress());
        } else {
            admin = new AdminDetails();
            admin.setAdminId(adminId);
            admin.setHospitalName(request.getHospitalName());
            admin.setHospitalPhoneNo(request.getHospitalPhoneNo());
            admin.setHospitalAddress(request.getHospitalAddress());
        }
        admin.getSpecialities().clear();
        // Convert List<String> to List<Speciality>
        List<Speciality> specialityList = request.getSpecialities().stream()
                .map(name -> {
                    Speciality speciality = new Speciality();
                    speciality.setName(name);
                    speciality.setHospital(admin);  // Set bidirectional link
                    return speciality;
                })
                .collect(Collectors.toList());


        admin.getSpecialities().addAll(specialityList);

        adminDetailsRepository.save(admin);

        return "Hospital details and specialties updated successfully";
    }
    public String updateDoctorProfile(DoctorDetails updatedData) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (!"doctor".equalsIgnoreCase(customer.getRole())) {
            throw new RuntimeException("Access denied: Not a doctor");
        }

        Long doctorId = customer.getId();
        Optional<DoctorDetails> existingDoctorOpt = doctorDetailsRepository.findById(doctorId);

        DoctorDetails doctor;
        if (existingDoctorOpt.isPresent()) {
            doctor = existingDoctorOpt.get();
            doctor.setFullname(updatedData.getFullname());
            doctor.setPhnno(updatedData.getPhnno());
            doctor.setSpeciality(updatedData.getSpeciality());
        } else {
            doctor = updatedData;
            doctor.setDoctorid(doctorId);

        }

        doctorDetailsRepository.save(doctor);
        return "Doctor profile updated successfully";
    }
}
