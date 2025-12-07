package com.example.study.domain.coverter;

import com.example.study.domain.StudentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StudentStatusConverter implements AttributeConverter<StudentStatus, String> {

    @Override
    public String convertToDatabaseColumn(StudentStatus status){
        if(status == null){
            return null;
        }
        return status.getCode();
    }

    @Override
    public StudentStatus convertToEntityAttribute(String dbStatus){
        if (dbStatus == null || dbStatus.isBlank()) {
            return null;
        }
        return StudentStatus.fromCode(dbStatus);
    }

}
