package com.example.sales_project.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;



import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MedicineDTO {

    @Id
    private String id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotBlank(message = "Manufacturer is mandatory")
    private String manufacturer;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 0, message = "Quantity must be zero or greater")
    private Integer quantity;

    @NotNull(message = "Expiration date is mandatory")
    @Future(message = "Expiration date must be in the future")
    private Date expirationDate;

    @NotBlank(message = "Dosage form is mandatory")
    private String dosageForm;


    private String classification; // E.g., Antibiotic, Pain Reliever, etc.
    private String countryOfOrigin;
    private String activeIngredients;
    private Integer storageTemperature; // in Celsius
    private String packagingType; // E.g., Bottle, Blister Pack, etc.
    private Integer minimumOrderQuantity;

    @NotNull(message = "Shelf life is mandatory")
    private Integer shelfLife; // in months

    private String sideEffects; // Optional
   






}
