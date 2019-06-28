package com.ccstudy.qna.exception.id;

public class SessionMismatchException extends IdMismatchException {
    public SessionMismatchException() {
        super("Session ID와 Path ID가 일치하지 않습니다.");
    }
}
