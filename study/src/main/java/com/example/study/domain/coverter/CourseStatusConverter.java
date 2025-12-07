package com.example.study.domain.coverter;

import com.example.study.domain.CourseStatus;
import com.example.study.domain.StudentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CourseStatusConverter implements AttributeConverter<CourseStatus, String> {

    public String convertToDatabaseColumn(CourseStatus status) {
        if(status == null){
            return null;
        }
        return status.getCode();
    }

    @Override
    public CourseStatus convertToEntityAttribute(String dbStatus) {
        if (dbStatus == null || dbStatus.isBlank()) {
            return null;
        }
        return CourseStatus.fromCode(dbStatus);
    }
}
