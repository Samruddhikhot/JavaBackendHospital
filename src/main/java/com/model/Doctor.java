package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    private String name;
    private String specialization;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "assignedDoctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Allows Doctor -> Patient serialization but avoids recursion back to Doctor
    @JsonIgnore
    private List<Patient> patients;
}
