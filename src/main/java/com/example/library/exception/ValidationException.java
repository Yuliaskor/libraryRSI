package com.example.library.exception;

import java.util.List;

public class ValidationException extends RuntimeException {
    private List<String> errors;

    public ValidationException(List<String> errors) {
        super("Validation errors: " + errors);
    }

    public List<String> getErrors() {
        return errors;
    }


}
