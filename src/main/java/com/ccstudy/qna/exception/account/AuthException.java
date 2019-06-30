package com.ccstudy.qna.exception.account;

import com.ccstudy.qna.exception.account.AccountException;

public class AuthException extends AccountException {
    public AuthException(String message) {
        super(message);
    }
}
