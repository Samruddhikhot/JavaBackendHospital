package com.dao;


import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	long countByDoctor_DoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime localDateTime, LocalDateTime localDateTime2);

}

