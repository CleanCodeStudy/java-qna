package com.ccstudy.qna.dto.Account;


import com.ccstudy.qna.domain.Account;
import lombok.Builder;
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

    @Builder(builderMethodName = "testBuilder")
    private AccountResDto(Long id, String userId, String email, String name) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

}
