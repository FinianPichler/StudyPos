package com.example.study.projection;

import com.example.study.domain.StudentStatus;

public record StudentSummaryProjection(
        Long studentId,
        String firstName,
        String lastName,
        StudentStatus studentStatus,
        Integer courseCount
) {}
