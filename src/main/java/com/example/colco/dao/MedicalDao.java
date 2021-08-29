package com.example.colco.dao;

import com.example.colco.entity.MedicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDao extends JpaRepository<MedicalEntity,Integer> {
    MedicalEntity findByEmpId(int id);
}
