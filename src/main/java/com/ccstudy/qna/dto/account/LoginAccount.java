package com.ccstudy.qna.dto.account;

import lombok.*;

import javax.servlet.http.HttpSession;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class LoginAccount {
    private String email;
    private String lastName;
    private String firstName;

    @Builder(builderMethodName = "createBuilder")
    public LoginAccount(String email, String lastName, String firstName) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public static LoginAccount getInstance(HttpSession httpSession){
        LoginAccount loginAccount = (LoginAccount)httpSession.getAttribute("LOGIN_ACCOUNT");
        return loginAccount;
    }

}

