package com.example.had_backend.Patient.Entity;

import com.example.had_backend.Doctor.Entity.Doctor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "patientL")
public class PatientL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer patientId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "refPatId", referencedColumnName = "patientId",nullable = false,unique = true)
    private Patient patient;
}
