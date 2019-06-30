package com.ccstudy.qna.domain;

import com.ccstudy.qna.exception.account.PasswordException;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    private String userId = "testId";
    private String password = "1234";
    private String name = "hong";
    private String email = "example@naver.com";
    private Account account;

    @Before
    public void setUp() throws Exception {
        account = Account.createBuilder()
                .userId(userId)
                .password(password)
                .name(name)
                .email(email)
                .build();
    }

    @Test(expected = PasswordException.class)
    public void 입력한_패스워드_값이_현재_패스워드_값과_같지_않습니다() {
        //given
        //when
        String checkPassword = "123";
        String changePassword = "12345";

        //then
        account.validatePassword(checkPassword, changePassword);
    }

    @Test(expected = PasswordException.class)
    public void 현재_패스워드와_바꾸려는_패스워드_값이_같습니다() {
        //given
        //when
        String checkPassword = "1234";
        String changePassword = "1234";

        //then
        account.validatePassword(checkPassword, changePassword);
    }
}