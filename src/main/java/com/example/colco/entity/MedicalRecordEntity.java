package com.example.colco.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "medicalre")
public class MedicalRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int empId;
    private String medName;
    private int price;
}
