package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DoctorRepository;
import com.exception.ResourceNotFoundException;
import com.model.Doctor;

@Service
public class DoctorServiceImp implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor with ID " + id + " not found"));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor existing = getDoctorById(id);
        existing.setName(updatedDoctor.getName());
        existing.setSpecialization(updatedDoctor.getSpecialization());
        existing.setEmail(updatedDoctor.getEmail());
        existing.setPhone(updatedDoctor.getPhone());
        return doctorRepository.save(existing);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
