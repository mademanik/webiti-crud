package com.webiti.crud.helper;

import lombok.Getter;

@Getter
public enum ValidationErrors {
    SYSTEM_ERROR("Sorry, system error(s) occurred"),
    EXTRACURRICULAR_NOT_FOUND("Data extracurricular not found"),
    MENTOR_NOT_FOUND("Data mentor not found");

    private final String defaultMessage;

    ValidationErrors(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
