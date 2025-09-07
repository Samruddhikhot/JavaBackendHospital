package com.service;

import java.util.List;

import com.model.Patient;

public interface PatientService {
	 Patient createPatient(Patient patient);
	    Patient getPatientById(Long id);
	    List<Patient> getAllPatients();
	    Patient updatePatient(Long id, Patient patient);
	    void deletePatient(Long id);
}
