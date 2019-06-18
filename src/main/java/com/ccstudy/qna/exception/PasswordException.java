package com.ccstudy.qna.exception;

public abstract class PasswordException extends RuntimeException {
    public PasswordException(String message) {
        super(message);
    }
}
