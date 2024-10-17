package com.example.sales_project.repository;

import com.example.sales_project.dto.MedicineDTO;

import com.example.sales_project.model.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, String> {
    List<MedicineEntity> findByManufacturer(String manufacturer);

}

