package com.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

	long countByDoctor_DoctorIdAndPrescribedDateBetween(Long doctorId, LocalDate start, LocalDate end);

}
