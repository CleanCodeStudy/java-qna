package com.ccstudy.qna.dto.Account;

import com.ccstudy.qna.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AccountSaveReqDto {
    @NotNull
    private String userId;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
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
