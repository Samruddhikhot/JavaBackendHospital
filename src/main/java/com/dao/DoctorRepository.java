package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
