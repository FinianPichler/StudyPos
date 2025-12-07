package com.example.study.domain;

public enum StudentStatus {
    ACTIVE("A"),
    INACTIVE("I"),
    GRADUATED("G");

    private final String code;

    StudentStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static StudentStatus fromCode(String code) {
        if (code == null) {
            return null;
        }
        for (StudentStatus status : StudentStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid student status code: " + code);
    }
}
