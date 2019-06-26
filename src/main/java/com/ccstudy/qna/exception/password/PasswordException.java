package com.ccstudy.qna.exception.password;

public abstract class PasswordException extends RuntimeException {
    public PasswordException(String message) {
        super(message);
    }
}
