package com.service;

import java.util.List;

import com.dto.PrescriptionDTO;
import com.model.Prescription;

public interface PrescriptionService {

	 Prescription createPrescription(Prescription prescription);
	    Prescription getPrescriptionById(Long id);
	  //  List<Prescription> getAllPrescriptions();
	    Prescription updatePrescription(Long id, Prescription prescription);
	    void deletePrescription(Long id);
		List<PrescriptionDTO> getAllPrescriptionDTOs();
}
