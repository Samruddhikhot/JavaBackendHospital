package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserRepository;
import com.dto.PatientDTO;
import com.model.Doctor;
import com.model.Patient;
import com.model.User;
import com.model.UserRole;
import com.service.AdminService;
import com.service.UserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/doctor")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return adminService.addDoctor(doctor);
    }

    @PostMapping("/patient")
    public Patient addPatient(@RequestBody Patient patient) {
        return adminService.addPatient(patient);
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return adminService.getAllDoctors();
    }
    @GetMapping("/patients")
    public List<PatientDTO> getAllPatients() {
        return adminService.getAllPatients();
    }

    @DeleteMapping("/doctor/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        adminService.deleteDoctor(id);
    }

    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable Long id) {
        adminService.deletePatient(id);
    }
    
    @PutMapping("/doctor/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return adminService.updateDoctor(id, doctor);
    }
    
    @PutMapping("/patient/{id}")
    public PatientDTO updatePatient(@PathVariable Long id, @RequestBody PatientDTO dto) {
        return adminService.updatePatient(id, dto);
    }

    @GetMapping("/getAllAdmins")
    public List<User> getAllAdmins() {
        return userRepository.findByRole(UserRole.ADMIN);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<User> updateAdmin(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        User admin = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Admin not found"));

        admin.setEmail(updates.get("email"));
        admin.setPassword(updates.get("password")); // store as plain text or hash in real app

        userRepository.save(admin);
        return ResponseEntity.ok(admin);
    }

}
