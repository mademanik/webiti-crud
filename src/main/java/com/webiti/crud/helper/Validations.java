package com.webiti.crud.helper;

import com.google.common.base.Preconditions;

public class Validations {
    public static void checkArgument(boolean expression, ValidationErrors error, Object... args) {
        try {
            Preconditions.checkArgument(expression);
        } catch (Exception e) {
            throw new ValidationException(error, args);
        }
    }
}
