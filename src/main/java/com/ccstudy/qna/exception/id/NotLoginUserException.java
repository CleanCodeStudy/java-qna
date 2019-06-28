package com.ccstudy.qna.exception.id;

public class NotLoginUserException extends IdMismatchException {
    public NotLoginUserException(String message) {
        super(message);
    }
}
