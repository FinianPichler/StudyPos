package com.example.study.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Column;

@Embeddable
public record Address(

        @NotBlank(message = "Street must not be blank")
        @Column(name = "street", nullable = false)
        String street,

        @NotBlank(message = "Zip code must not be blank")
        @Pattern(regexp = "\\d{4}", message = "Zip code must be 4 digits")
        @Column(name = "zipCode", nullable = false)
        String zipCode,

        @NotBlank(message = "City must not be blank")
        @Column(name = "city", nullable = false)
        String city
) {

    public Address {
        if (street != null && street.isBlank()){
            throw new IllegalArgumentException("Street must not be blank");
        }
        if(zipCode != null && zipCode.isBlank()){
            throw new IllegalArgumentException("Zip code must not be blank");
        }
        if (city != null && city.isBlank()){
            throw new IllegalArgumentException("City must not be blank");
        }
    }
}
