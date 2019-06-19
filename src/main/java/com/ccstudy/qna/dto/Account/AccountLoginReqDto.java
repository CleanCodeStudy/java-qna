package com.ccstudy.qna.dto.Account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountLoginReqDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @Builder(builderMethodName = "testBuilder")
    private AccountLoginReqDto(@NotBlank String userId, @NotBlank String password) {
        this.userId = userId;
        this.password = password;
    }
}
