package com.webiti.crud.helper;

import lombok.Getter;

@Getter
public enum ValidationErrors {
    SYSTEM_ERROR("Sorry, system error(s) occurred"),
    USER_NOT_FOUND("Data user not found"),
    USER_ALREADY_EXISTS("User already exists"),
    ADMIN_ALREADY_EXISTS("Admin already exists"),
    PRODUCT_NOT_FOUND("Data product not found"),
    ORDER_NOT_FOUND("Data order not found");

    private final String defaultMessage;

    ValidationErrors(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
