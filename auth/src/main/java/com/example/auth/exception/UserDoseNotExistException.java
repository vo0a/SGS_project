package com.example.auth.exception;


public class UserDoseNotExistException extends RuntimeException {

    public UserDoseNotExistException(String message) {
        super(message);
    }

    public UserDoseNotExistException() {
        super("등록된 회원이 아닙니다.");
    }
}
