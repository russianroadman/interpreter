package com.example.demo.exception;

public class ActionNotRecognizedException extends IllegalArgumentException {

    public ActionNotRecognizedException(String s) {
        super(s);
    }
}
