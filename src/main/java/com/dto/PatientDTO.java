package com.dto;


import java.time.LocalDate;

public class PatientDTO {
 private Long patientId;
 private String name;
 private String email;
 private String phone;
 private LocalDate dob;
 private String gender;
 private Long doctorId; // flatten doctor_id
 
 
 
public PatientDTO() {
	super();
}
public PatientDTO(Long patientId, String name, String email, String phone, LocalDate dob, String gender,
		Long doctorId) {
	super();
	this.patientId = patientId;
	this.name = name;
	this.email = email;
	this.phone = phone;
	this.dob = dob;
	this.gender = gender;
	this.doctorId = doctorId;
}
public Long getPatientId() {
	return patientId;
}
public void setPatientId(Long patientId) {
	this.patientId = patientId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public LocalDate getDob() {
	return dob;
}
public void setDob(LocalDate dob) {
	this.dob = dob;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public Long getDoctorId() {
	return doctorId;
}
public void setDoctorId(Long doctorId) {
	this.doctorId = doctorId;
}

 
 
}
