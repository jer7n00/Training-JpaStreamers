package com.example.sales_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "medicines")
public class MedicineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "expiration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "dosage_form", nullable = false)
    private String dosageForm;

    @Column(name = "classification")
    private String classification;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "active_ingredients")
    private String activeIngredients;

    @Column(name = "storage_temperature")
    private Integer storageTemperature;

    @Column(name = "packaging_type")
    private String packagingType;

    @Column(name = "minimum_order_quantity")
    private Integer minimumOrderQuantity;

    @Column(name = "shelf_life", nullable = false)
    private Integer shelfLife;

    @Column(name = "side_effects")
    private String sideEffects;
}
