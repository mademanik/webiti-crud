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

    UNAUTHORIZED_UPDATED_USER("You are not authorized to update this user"),

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
