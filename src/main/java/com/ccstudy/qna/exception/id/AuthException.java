package com.ccstudy.qna.exception.id;

public abstract class AuthException extends RuntimeException {
    AuthException(String message) {
        super(message);
    }
}
