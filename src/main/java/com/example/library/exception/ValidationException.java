package com.example.library.exception;

import java.util.List;

public class ValidationException extends RuntimeException {

    public ValidationException(List<String> errors) {
        super("Validation errors: " + errors);
    }

}
