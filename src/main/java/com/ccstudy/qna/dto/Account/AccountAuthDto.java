package com.ccstudy.qna.dto.Account;

import com.ccstudy.qna.domain.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountAuthDto {
    public static String ATTRIBUTE_NAME = "accountId";

    private Long id;

    @Builder(builderMethodName = "createBuilder")
    private AccountAuthDto(Account account) {
        this.id = account.getId();
    }
}
