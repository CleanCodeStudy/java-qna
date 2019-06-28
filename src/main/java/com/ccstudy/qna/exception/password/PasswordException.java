package com.ccstudy.qna.exception.password;

public abstract class PasswordException extends RuntimeException {
    PasswordException(String message) {
        super(message);
    }
}
