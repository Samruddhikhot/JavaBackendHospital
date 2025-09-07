package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DoctorRepository;
import com.dao.PatientRepository;
import com.dto.PatientDTO;
import com.model.Doctor;
import com.model.Patient;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream().map(patient -> {
            PatientDTO dto = new PatientDTO();
            dto.setPatientId(patient.getPatientId());
            dto.setName(patient.getName());
            dto.setEmail(patient.getEmail());
            dto.setPhone(patient.getPhone());
            dto.setDob(patient.getDob());
            dto.setGender(patient.getGender());
            dto.setDoctorId(patient.getAssignedDoctor() != null ? patient.getAssignedDoctor().getDoctorId() : null);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }
    
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setName(doctorDetails.getName());
        doctor.setEmail(doctorDetails.getEmail());
        doctor.setPhone(doctorDetails.getPhone());
        doctor.setSpecialization(doctorDetails.getSpecialization());

        return doctorRepository.save(doctor);
    }
    
    public PatientDTO updatePatient(Long id, PatientDTO dto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        patient.setDob(dto.getDob());
        patient.setGender(dto.getGender());

        if (dto.getDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            patient.setAssignedDoctor(doctor);
        } else {
            patient.setAssignedDoctor(null);
        }

        Patient saved = patientRepository.save(patient);

        // return DTO again
        PatientDTO response = new PatientDTO();
        response.setPatientId(saved.getPatientId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        response.setPhone(saved.getPhone());
        response.setDob(saved.getDob());
        response.setGender(saved.getGender());
        response.setDoctorId(saved.getAssignedDoctor() != null ? saved.getAssignedDoctor().getDoctorId() : null);
        return response;
    }

	
}


