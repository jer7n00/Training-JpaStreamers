package com.example.sales_project.service;



import com.example.sales_project.dto.MedicineDTO;
import com.example.sales_project.repository.MedicineRepository;
import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sales_project.model.MedicineEntity;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public MedicineService(final JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }

    public MedicineEntity initiateMedicine(MedicineDTO medicineDTO) {
        MedicineEntity medicine = MedicineEntity.builder()
                .name(medicineDTO.getName())
                .description(medicineDTO.getDescription())
                .manufacturer(medicineDTO.getManufacturer())
                .price(medicineDTO.getPrice())
                .quantity(medicineDTO.getQuantity())
                .expirationDate(medicineDTO.getExpirationDate())
                .dosageForm(medicineDTO.getDosageForm())
                .classification(medicineDTO.getClassification())
                .countryOfOrigin(medicineDTO.getCountryOfOrigin())
                .activeIngredients(medicineDTO.getActiveIngredients())
                .storageTemperature(medicineDTO.getStorageTemperature())
                .packagingType(medicineDTO.getPackagingType())
                .minimumOrderQuantity(medicineDTO.getMinimumOrderQuantity())
                .shelfLife(medicineDTO.getShelfLife())
                .sideEffects(medicineDTO.getSideEffects())
                .build();

        return medicineRepository.save(medicine);
    }


    public List<MedicineEntity> getAllDrugs() {
        return medicineRepository.findAll();
    }

    public MedicineEntity getDrugById(String id) {
        return medicineRepository.findById(id).orElse(null);
    }




    public void deleteDrug(String id) {
        medicineRepository.deleteById(id);
    }

    public Map<String, List<MedicineEntity>> groupbyClassification() {
        return jpaStreamer.stream(MedicineEntity.class)
                .collect(Collectors.groupingBy(MedicineEntity::getClassification));
    }

    public Map<String, Long> countDrugsByCountryOfOrigin() {
        return jpaStreamer.stream(MedicineEntity.class)
                .collect(Collectors.groupingBy(MedicineEntity::getCountryOfOrigin, Collectors.counting()));
    }

    public Map<String, Long> groupDrugsByManufacturerUnderPrice(double price) {
        return jpaStreamer.stream(MedicineEntity.class)
                .filter(drug -> drug.getPrice() <= price)
                .collect(Collectors.groupingBy(MedicineEntity::getManufacturer, Collectors.counting()));
    }

    public List<MedicineEntity> findDrugsByManufacturerAndExpiringBefore(String manufacturer, Date expirationDate) {
        List<MedicineEntity> drugsByManufacturer = medicineRepository.findByManufacturer(manufacturer);
        return drugsByManufacturer.stream()
                .filter(drug -> drug.getExpirationDate().before(expirationDate))
                .toList();
    }


}

