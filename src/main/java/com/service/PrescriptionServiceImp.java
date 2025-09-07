package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PrescriptionRepository;
import com.dto.PrescriptionDTO;
import com.exception.ResourceNotFoundException;
import com.model.Prescription;

@Service
public class PrescriptionServiceImp implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription with ID " + id + " not found"));
    }

  

    public List<PrescriptionDTO> getAllPrescriptionDTOs() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();
        return prescriptions.stream().map(p -> {
            PrescriptionDTO dto = new PrescriptionDTO();
            dto.setId(p.getId());
            dto.setMedicine(p.getMedicine());
            dto.setDosage(p.getDosage());
            dto.setInstructions(p.getInstructions());
            dto.setPrescribedDate(p.getPrescribedDate());
            dto.setDoctorId(p.getDoctor() != null ? p.getDoctor().getDoctorId() : null);
            dto.setPatientId(p.getPatient() != null ? p.getPatient().getPatientId() : null);
            dto.setPatientName(p.getPatient() != null ? p.getPatient().getName() : "-");
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Prescription updatePrescription(Long id, Prescription updated) {
        Prescription existing = getPrescriptionById(id);
        existing.setMedicine(updated.getMedicine());
        existing.setDosage(updated.getDosage());
        existing.setInstructions(updated.getInstructions());
        existing.setPrescribedDate(updated.getPrescribedDate());
        existing.setPatient(updated.getPatient());
        existing.setDoctor(updated.getDoctor());
        return prescriptionRepository.save(existing);
    }

    @Override
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}