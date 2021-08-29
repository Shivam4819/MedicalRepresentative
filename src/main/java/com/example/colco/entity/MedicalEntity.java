package com.example.colco.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name= "mrdetails")
public class MedicalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int empId;
    private String empName;
    private String address;

}
