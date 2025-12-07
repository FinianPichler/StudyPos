package com.example.study.domain.coverter;

import com.example.study.domain.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email email) {
        if (email == null) {
            return null;
        }
        return null;
    }

    @Override
    public Email convertToEntityAttribute(String dbEmail) {
        if (dbEmail  == null || dbEmail.isBlank()) {
            return null;
        }
        return new Email(dbEmail);
    }
}
