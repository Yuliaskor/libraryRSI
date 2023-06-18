package com.example.library.exception;

public class BookAlreadyExistException extends RuntimeException {

    public BookAlreadyExistException() {
        super("Book already exist");
    }
}
