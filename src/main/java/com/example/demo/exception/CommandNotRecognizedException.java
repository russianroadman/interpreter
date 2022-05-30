package com.example.demo.exception;

public class CommandNotRecognizedException
        extends UnsupportedOperationException {

    public CommandNotRecognizedException(String message) {
        super(message);
    }
}
