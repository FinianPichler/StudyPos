package com.example.study.projection;

import com.example.study.domain.CourseStatus;

public record CourseInfoProjection(
        Long courseId,
        String courseName,
        Integer credits,
        CourseStatus courseStatus,
        int enrolledStudents
) {
}
