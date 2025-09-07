package com.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private LocalDateTime appointmentDate;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference // Avoid infinite recursion when serializing Patient -> Appointment
    @JsonIgnoreProperties({"appointments","bills","prescriptions","assignedDoctor"})
    
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnoreProperties({"patients"})
    private Doctor doctor;
}
