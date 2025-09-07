package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BillingRepository;
import com.dao.PatientRepository;
import com.exception.ResourceNotFoundException;
import com.model.Billing;
import com.model.Patient;

@Service
public class BillingServiceImp implements BillingService {

    @Autowired
    private BillingRepository billingRepository;
    
    @Autowired
    private PatientRepository patientRepository; // Make sure you have this repo

    
    @Override
    public Billing getBillingById(Long id) {
        return billingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Billing record with ID " + id + " not found"));
    }

    @Override
    public List<Billing> getAllBillings() {
        return billingRepository.findAll();
    }
    
    

    @Override
    public Billing createBilling(Billing billing) {
        if (billing.getPatient() != null && billing.getPatient().getPatientId() != null) {
            Patient patient = patientRepository.findById(billing.getPatient().getPatientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
            billing.setPatient(patient);
        }
        billing.setStatus(billing.getStatus() != null ? billing.getStatus() : "PENDING");
        return billingRepository.save(billing);
    }

    @Override
    public Billing updateBilling(Long id, Billing updatedBilling) {
        Billing existing = getBillingById(id);

        existing.setBillingDate(updatedBilling.getBillingDate());
        existing.setAmount(updatedBilling.getAmount());
        existing.setDescription(updatedBilling.getDescription());
        existing.setStatus(updatedBilling.getStatus());

        if (updatedBilling.getPatient() != null && updatedBilling.getPatient().getPatientId() != null) {
            Patient patient = patientRepository.findById(updatedBilling.getPatient().getPatientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
            existing.setPatient(patient);
        }

        return billingRepository.save(existing);
    }


    @Override
    public void deleteBilling(Long id) {
        billingRepository.deleteById(id);
    }
}
