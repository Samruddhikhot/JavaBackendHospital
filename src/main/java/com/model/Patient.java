
package com.model;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    private String name;
    private String email;
    private String phone;
    private LocalDate dob;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference // Prevent infinite recursion: Patient -> Doctor -> Patients
    //@JsonIgnore
    private Doctor assignedDoctor;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Allow serialization of Patient -> Appointments without looping
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Allow serialization of Patient -> Bills without looping
    @JsonIgnore
    private List<Billing> bills;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Allow serialization of Patient -> Prescriptions
    @JsonIgnore
    private List<Prescription> prescriptions;
}
