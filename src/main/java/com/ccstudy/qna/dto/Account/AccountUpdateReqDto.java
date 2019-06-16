package com.ccstudy.qna.dto.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AccountUpdateReqDto {
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String currentPassword;

    @NotNull
    @NotBlank
    private String changePassword;



}
