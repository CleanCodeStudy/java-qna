package com.ccstudy.qna.dto.Account;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AccountUpdateReqDto {
    @NotNull
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String password;
}
