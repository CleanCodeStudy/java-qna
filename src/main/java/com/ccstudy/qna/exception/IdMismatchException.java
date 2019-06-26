package com.ccstudy.qna.exception;

public abstract class IdMismatchException extends RuntimeException {
    public IdMismatchException(String message) {
        super(message);
    }
}
