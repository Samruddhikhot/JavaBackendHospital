package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findByEmail(String email);
	
   
}
