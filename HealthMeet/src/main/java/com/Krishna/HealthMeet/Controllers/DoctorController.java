package com.Krishna.HealthMeet.Controllers;

import com.Krishna.HealthMeet.Entity.Customer;
import com.Krishna.HealthMeet.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DoctorController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> request, Authentication authentication) {
        try {
            String email = authentication.getName(); // Authenticated user from HTTP Basic Auth
            Customer customer = customerRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Customer account not found"));



            String newPassword = request.get("Password");
            if (newPassword == null || newPassword.isEmpty()) {
                return ResponseEntity.badRequest().body("New password must not be empty");
            }

            customer.setPassword(passwordEncoder.encode(newPassword));
            customerRepository.save(customer);

            return ResponseEntity.ok("Password updated successfully.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update password: " + ex.getMessage());
        }
    }

}
