package com.ccstudy.qna.dto.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AccountUpdateReqDto {
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String password;
}
