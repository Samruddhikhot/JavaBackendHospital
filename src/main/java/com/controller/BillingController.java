package com.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.PatientRepository;
import com.dto.BillingDTO;
import com.model.Billing;
import com.model.Patient;
import com.service.BillingService;

@RestController
@RequestMapping("/api/billings")
@CrossOrigin(origins = "http://localhost:3000")
public class BillingController {

    @Autowired
    private BillingService billingService;
    
    @Autowired
    private PatientRepository patientRepository;


    @GetMapping("/{id}")
    public Billing getBillingById(@PathVariable Long id) {
        return billingService.getBillingById(id);
    }

    @GetMapping("/getAll")
    public List<BillingDTO> getAllBillings() {
        return billingService.getAllBillings().stream().map(b -> {
            BillingDTO dto = new BillingDTO();
            dto.setBillingId(b.getBillingId()); // <--- Add this
            dto.setBillingDate(b.getBillingDate().toString());
            dto.setAmount(b.getAmount());
            dto.setDescription(b.getDescription());
            dto.setPatientId(b.getPatient() != null ? b.getPatient().getPatientId() : null);
            dto.setStatus(b.getStatus());
            return dto;
        }).toList();
    }


    @PostMapping("/create")
    public Billing createBilling(@RequestBody BillingDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Billing billing = Billing.builder()
                .billingDate(LocalDate.parse(dto.getBillingDate()))
                .amount(dto.getAmount())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .patient(patient)
                .build();
        return billingService.createBilling(billing);
    }

    @PutMapping("/{id}")
    public Billing updateBilling(@PathVariable Long id, @RequestBody BillingDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Billing updatedBilling = Billing.builder()
                .billingDate(LocalDate.parse(dto.getBillingDate()))
                .amount(dto.getAmount())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .patient(patient)
                .build();
        return billingService.updateBilling(id, updatedBilling);
    }


    @DeleteMapping("/{id}")
    public void deleteBilling(@PathVariable Long id) {
        billingService.deleteBilling(id);
    }
}
