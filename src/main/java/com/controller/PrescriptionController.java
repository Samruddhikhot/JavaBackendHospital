package com.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.PrescriptionRepository;
import com.dto.PrescriptionDTO;
import com.model.Prescription;
import com.service.PrescriptionService;

@RestController
@RequestMapping("/api/prescriptions")
@CrossOrigin(origins = "http://localhost:3000")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;
    
    @Autowired
    private PrescriptionRepository  prescriptionRepository;

    @PostMapping("/create")
    public Prescription createPrescription(@RequestBody Prescription prescription) {
        return prescriptionService.createPrescription(prescription);
    }

    @GetMapping("/{id}")
    public Prescription getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }
    
//    @GetMapping("/getAll")
//    public List<Prescription> getAllPrescriptions() {
//        return prescriptionService.getAllPrescriptions();
//    }
    
    @GetMapping("/getAll")
    public List<PrescriptionDTO> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptionDTOs();
    }


    @PutMapping("/{id}")
    public Prescription updatePrescription(@PathVariable Long id, @RequestBody Prescription prescription) {
        return prescriptionService.updatePrescription(id, prescription);
    }

    @DeleteMapping("/{id}")
    public void deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
    }
    
    @GetMapping("/weeklySummary")
    public Map<String, Long> getWeeklySummary(@RequestParam Long doctorId) {
        long count = prescriptionRepository.countByDoctor_DoctorIdAndPrescribedDateBetween(
            doctorId, 
            LocalDate.now().with(DayOfWeek.MONDAY),
            LocalDate.now().with(DayOfWeek.SUNDAY)
        );
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);
        return response;
    }

}
