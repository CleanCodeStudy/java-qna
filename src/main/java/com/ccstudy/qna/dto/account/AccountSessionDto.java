package com.ccstudy.qna.dto.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public static AccountSessionDto getInstance(HttpSession httpSession) {
        Optional<AccountSessionDto> maybeLoginSession = Optional.ofNullable((AccountSessionDto) httpSession.getAttribute("LOGIN_ACCOUNT"));
        AccountSessionDto accountSessionDto = maybeLoginSession.orElseThrow(NoSuchElementException::new);
        return accountSessionDto;
    }

}

