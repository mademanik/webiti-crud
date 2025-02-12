package com.webiti.crud.helper;

import lombok.Getter;

@Getter
public enum ValidationErrors {
    SYSTEM_ERROR("Sorry, system error(s) occurred"),
    USER_NOT_FOUND("Data user not found");

    private final String defaultMessage;

    ValidationErrors(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
