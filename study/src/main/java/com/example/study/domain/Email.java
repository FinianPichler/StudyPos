package com.example.study.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;


@Embeddable
public record Email(
        @NotBlank(message = "Email address cannot be blank or empty")
        @Column(name= "email")
        String address) {

    public Email {
        if (address != null && !address.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
