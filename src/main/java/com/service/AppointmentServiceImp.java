package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AppointmentRepository;
import com.dto.AppointmentDTO;
import com.exception.ResourceNotFoundException;
import com.model.Appointment;

@Service
public class AppointmentServiceImp implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment with ID " + id + " not found"));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment updated) {
        Appointment existing = getAppointmentById(id);
        existing.setAppointmentDate(updated.getAppointmentDate());
        existing.setReason(updated.getReason());
        existing.setPatient(updated.getPatient());
        existing.setDoctor(updated.getDoctor());
        return appointmentRepository.save(existing);
    }
    
    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
    
    

    @Override
    public List<AppointmentDTO> getAllAppointmentsDTO() {
        return appointmentRepository.findAll().stream().map(a -> {
            AppointmentDTO dto = new AppointmentDTO();
            dto.setAppointmentId(a.getAppointmentId());
            dto.setAppointmentDate(a.getAppointmentDate());
            dto.setReason(a.getReason());
            if (a.getPatient() != null) {
                dto.setPatientId(a.getPatient().getPatientId());
                dto.setPatientName(a.getPatient().getName());
            }
            if (a.getDoctor() != null) {
                dto.setDoctorId(a.getDoctor().getDoctorId());
                dto.setDoctorName(a.getDoctor().getName());
            }
            return dto;
        }).toList();
    }

}