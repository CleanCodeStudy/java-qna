package com.ccstudy.qna.exception;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException() {
        super("비밀번호 아이디가 일치하지 않습니다.");
    }
}
