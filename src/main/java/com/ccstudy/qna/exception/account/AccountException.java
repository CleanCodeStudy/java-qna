package com.ccstudy.qna.exception.account;

public abstract class AccountException extends RuntimeException{
    AccountException(String message) {
        super(message);
    }
}
