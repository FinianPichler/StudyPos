package com.example.study.domain;

public enum CourseStatus {
    PLANNED("P"),
    RUNNING("R"),
    COMPLETED("C"),
    CANCELLED("X");

    private final String code;

    CourseStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CourseStatus fromCode(String code) {
        if (code == null) {
            for (CourseStatus status : CourseStatus.values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
        }
        throw new IllegalArgumentException("Invalid course status code: " + code);
    }
}
