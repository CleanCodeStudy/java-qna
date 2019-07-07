package com.ccstudy.qna.exception.account;

public abstract class QuestionException extends RuntimeException {
    QuestionException(String message) {
        super(message);
    }
}
