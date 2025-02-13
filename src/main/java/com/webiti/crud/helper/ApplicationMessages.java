package com.webiti.crud.helper;

import lombok.Getter;

@Getter
public enum ApplicationMessages {
    SYSTEM_ERROR("Sorry, something bad has occurred : %s"),

    USER_CREATED("Data user successfully created"),
    USER_RETRIEVED("Data user successfully retrieved"),
    USER_DELETED("Data user successfully deleted"),
    USER_UPDATED("Data user successfully updated"),
    USER_DATA_EMPTY("Data user is empty"),
    USER_NOT_FOUND("Data user not found with id : %s"),

    PRODUCT_CREATED("Data product successfully created"),
    PRODUCT_RETRIEVED("Data product successfully retrieved"),
    PRODUCT_DELETED("Data product successfully deleted"),
    PRODUCT_UPDATED("Data product successfully updated"),
    PRODUCT_DATA_EMPTY("Data product is empty"),
    PRODUCT_NOT_FOUND("Data product not found with id : %s"),

    ORDER_CREATED("Data order successfully created"),
    ORDER_RETRIEVED("Data order successfully retrieved"),
    ORDER_DELETED("Data order successfully deleted"),
    ORDER_UPDATED("Data order successfully updated"),
    ORDER_DATA_EMPTY("Data order is empty"),
    ORDER_NOT_FOUND("Data order not found with id : %s"),

    USER_STAT_RETRIEVED("Data user statistics successfully retrieved"),
    PRODUCT_STAT_RETRIEVED("Data product statistics successfully retrieved"),
    ORDER_STAT_RETRIEVED("Data order statistics successfully retrieved"),

    UNAUTHORIZED_UPDATED_USER("You are not authorized to update this user"),
    UNAUTHORIZED_GET_USER("You are not authorized to get this user"),

    UNAUTHORIZED_CREATE_ORDER("You are not authorized to create order for this user"),
    UNAUTHORIZED_GET_ORDER("You are not authorized to get order data from this user"),
    UNAUTHORIZED_DELETE_ORDER("You are not authorized to delete order data from this user"),
    UNAUTHORIZED_UPDATE_ORDER("You are not authorized to update order data from this user"),

    LOGIN_SUCCESS("Login success"),
    REGISTER_SUCCESS("Register success"),
    ADMIN_CREATED("Admin successfully created"),
    REFRESH_TOKEN_SUCCESS("Refresh token successfully generated"),

    USERNAME_PASSWORD_INCORRECT("The username or password is incorrect"),
    ACCOUNT_LOCKED("The account is locked"),
    UNAUTHORIZED("You are not authorized to access this resource"),
    JWT_SIGNATURE_INVALID("The JWT signature is invalid"),
    JWT_TOKEN_EXPIRED("The JWT token has expired");

    private final String message;

    ApplicationMessages(String message) {
        this.message = message;
    }

    public String getValue(String additionalMessage) {
        return String.format(message, additionalMessage);
    }
}
