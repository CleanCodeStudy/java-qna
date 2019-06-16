package com.ccstudy.qna.dto.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccountSessionDto {
    private String email;
    private String lastName;
    private String firstName;

    @Builder(builderMethodName = "createBuilder")
    public AccountSessionDto(String email, String lastName, String firstName) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}

