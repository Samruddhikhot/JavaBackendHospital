package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dao.UserRepository;
import com.dao.PatientRepository;
import com.dto.RegisterRequest;
import com.model.User;
import com.model.Patient;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")  // allow frontend
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // NOTE: use hashing in real app
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully with role: " + user.getRole());
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisterRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .map(user -> {
                    if (user.getPassword().equals(request.getPassword())) {
                        Map<String, Object> response = new HashMap<>();
                        response.put("userId", user.getUserId());
                        response.put("username", user.getUsername());
                        response.put("email", user.getEmail());
                        response.put("role", user.getRole());

                        // Attach patientId if role = PATIENT
                        if ("PATIENT".equalsIgnoreCase(user.getRole().name())) {
                            Patient patient = patientRepository.findByEmail(user.getEmail());
                            if (patient != null) {
                                response.put("patientId", patient.getPatientId());
                            }
                        }

                        return ResponseEntity.ok(response);
                    } else {
                        return ResponseEntity.status(401).body("Invalid password");
                    }
                })
                .orElse(ResponseEntity.status(404).body("User not found"));
    }
}

