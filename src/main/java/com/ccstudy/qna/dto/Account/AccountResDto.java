package com.ccstudy.qna.dto.Account;


import com.ccstudy.qna.domain.Account;
import lombok.Getter;

@Getter
public class AccountResDto {
    private Long id;
    private String userId;
    private String email;
    private String name;

    public AccountResDto(Account account) {
        this.id = account.getId();
        this.userId = account.getUserId();
        this.email = account.getEmail();
        this.name = account.getName();
    }
}
