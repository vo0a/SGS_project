package com.example.auth.exception;

public class EmailSigninFailedException extends RuntimeException {

    public EmailSigninFailedException(String msg) {
        super(msg);
    }

    public EmailSigninFailedException() {
        super();
    }
}