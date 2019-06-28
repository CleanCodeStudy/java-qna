package com.ccstudy.qna.exception.id;

public abstract class IdMismatchException extends RuntimeException {
    IdMismatchException(String message) {
        super(message);
    }
}
