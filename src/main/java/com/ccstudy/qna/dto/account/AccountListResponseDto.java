package com.ccstudy.qna.dto.account;

import com.ccstudy.qna.util.StringConverter;
import com.ccstudy.qna.domain.account.Account;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountListResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String registerDate;

    @Builder
    public AccountListResponseDto(Account entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.registerDate = StringConverter.toStringDate(entity.getRegisterDate());
    }
}
