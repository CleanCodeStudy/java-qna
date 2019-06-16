package com.ccstudy.qna.dto.Account;

import com.ccstudy.qna.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AccountSaveReqDto {
    @NotNull
    @NotBlank
    private String userId;

    @NotNull
    @Email
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String name;

    public Account toEntity() {
        return Account.builder()
                .userId(this.userId)
                .email(this.email)
                .name(this.name)
                .password(this.password)
                .build();
    }
}
