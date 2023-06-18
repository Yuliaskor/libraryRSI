package com.example.library.exception;

public class AuthorAlreadyExistException extends RuntimeException {

    public AuthorAlreadyExistException() {

        super("Author already exist");
    }
}