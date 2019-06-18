package com.ccstudy.qna.dto.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
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
