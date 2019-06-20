package com.ccstudy.qna.exception;

public class NotLoginUserException extends RuntimeException{
    public NotLoginUserException(String message) {
        super(message);
    }
}
