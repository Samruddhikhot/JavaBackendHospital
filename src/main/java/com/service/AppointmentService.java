package com.service;

import java.util.List;

import com.dto.AppointmentDTO;
import com.model.Appointment;

public interface AppointmentService {
	Appointment createAppointment(Appointment appointment);
    Appointment getAppointmentById(Long id);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
	List<AppointmentDTO> getAllAppointmentsDTO();
}
