package com.example.colco.dao;

import com.example.colco.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordDao extends JpaRepository<MedicalRecordEntity,Integer> {
    List<MedicalRecordEntity> findAllByEmpId(int empId);

    void deleteALLByEmpId(int empId);

    MedicalRecordEntity findByEmpIdAndAndMedName(int id, String name);

    Integer deleteByEmpIdAndMedName(Integer id,String name);

}
