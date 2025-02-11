package com.webiti.crud.helper;

import lombok.Getter;

@Getter
public enum ApplicationMessages {
    SYSTEM_ERROR("Sorry, something bad has occurred : %s"),
    EXTRACURRICULAR_CREATED("Data extracurricular successfully created"),
    EXTRACURRICULAR_RETRIEVED("Data extracurricular successfully retrieved"),
    EXTRACURRICULAR_DELETED("Data extracurricular successfully deleted"),
    EXTRACURRICULAR_UPDATED("Data extracurricular successfully updated"),
    EXTRACURRICULAR_DATA_EMPTY("Data extracurricular is empty"),
    EXTRACURRICULAR_NOT_FOUND("Data extracurricular not found with id : %s"),

    USER_CREATED("Data user successfully created"),
    USER_RETRIEVED("Data user successfully retrieved"),
    USER_DELETED("Data user successfully deleted"),
    USER_UPDATED("Data user successfully updated"),
    USER_DATA_EMPTY("Data user is empty"),
    USER_NOT_FOUND("Data user not found with id : %s"),

    LOGIN_SUCCESS("Login success"),
    REGISTER_SUCCESS("Register success"),
    ADMIN_CREATED("Admin successfully created"),

    MENTOR_CREATED("Data mentor successfully created"),
    MENTOR_RETRIEVED("Data mentor successfully retrieved"),
    MENTOR_DELETED("Data mentor successfully deleted"),
    MENTOR_UPDATED("Data mentor successfully updated"),
    MENTOR_DATA_EMPTY("Data mentor is empty"),
    MENTOR_NOT_FOUND("Data mentor not found with id : %s"),

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
