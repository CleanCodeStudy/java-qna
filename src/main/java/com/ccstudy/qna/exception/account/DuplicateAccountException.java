package com.ccstudy.qna.exception.account;

public class DuplicateAccountException extends AccountException {
    public DuplicateAccountException(String id) {
        super(id + " 로 중복된 아이디가 존재합니다.");
    }
}
