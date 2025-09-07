package com.service;

import java.util.List;

import com.dto.PatientDTO;
import com.model.Doctor;
import com.model.Patient;

public interface AdminService {
	Doctor addDoctor(Doctor doctor);
    Patient addPatient(Patient patient);
    List<Doctor> getAllDoctors();
//    List<Patient> getAllPatients();
    void deleteDoctor(Long doctorId);
    void deletePatient(Long patientId);
	Doctor updateDoctor(Long id, Doctor doctor);
	//Patient updatePatient(Long id, Patient patient);
	List<PatientDTO> getAllPatients();
	PatientDTO updatePatient(Long id, PatientDTO dto);
}
