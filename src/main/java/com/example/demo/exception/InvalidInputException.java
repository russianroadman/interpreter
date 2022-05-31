package com.example.demo.exception;

public class InvalidInputException extends IllegalStateException {

    public InvalidInputException(String s) {
        super(s);
    }
}
