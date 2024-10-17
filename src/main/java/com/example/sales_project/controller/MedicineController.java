package com.example.sales_project.controller;

import com.example.sales_project.dto.MedicineDTO;
import com.example.sales_project.model.MedicineEntity;
import com.example.sales_project.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/drugs")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public ResponseEntity<List<MedicineEntity>> getAllDrugs() {
        List<MedicineEntity> drugs = medicineService.getAllDrugs();
        return ResponseEntity.ok(drugs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineEntity> getDrugById(@PathVariable String id) {
        MedicineEntity drug = medicineService.getDrugById(id);
        return drug != null ? ResponseEntity.ok(drug) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MedicineEntity> initiateMedicine(@RequestBody @Valid MedicineDTO drug) {
       return new ResponseEntity<>(medicineService.initiateMedicine(drug), HttpStatus.OK);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrug(@PathVariable String id) {
        medicineService.deleteDrug(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MedicineEntity>> findDrugsByManufacturerAndExpiringBefore(
            @RequestParam String manufacturer,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date expirationDate) {
        List<MedicineEntity> drugs = medicineService.findDrugsByManufacturerAndExpiringBefore(manufacturer, expirationDate);
        return ResponseEntity.ok(drugs);
    }

    @GetMapping("/groupByClassification")
    public ResponseEntity<Map<String, List<MedicineEntity>>> groupByClassification() {
        Map<String, List<MedicineEntity>> groupedDrugs = medicineService.groupbyClassification();
        return ResponseEntity.ok(groupedDrugs);
    }

    @GetMapping("/countByCountry")
    public ResponseEntity<Map<String, Long>> countDrugsByCountryOfOrigin() {
        Map<String, Long> drugCounts = medicineService.countDrugsByCountryOfOrigin();
        return ResponseEntity.ok(drugCounts);
    }

    @GetMapping("/groupByManufacturerUnderPrice/{price}")
    public ResponseEntity<Map<String, Long>> groupDrugsByManufacturerUnderPrice(@PathVariable("price") double price) {
        Map<String, Long> groupedDrugs = medicineService.groupDrugsByManufacturerUnderPrice(price);
        return ResponseEntity.ok(groupedDrugs);
    }
}

