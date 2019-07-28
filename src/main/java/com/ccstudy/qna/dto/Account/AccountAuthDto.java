package com.ccstudy.qna.dto.Account;

import com.ccstudy.qna.domain.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountAuthDto {
    public static String ATTRIBUTE_NAME = "accountId";

    @NotNull
    private Long id;

    @Builder(builderMethodName = "createBuilder")
    private AccountAuthDto(Account account) {
        this.id = account.getId();
    }
}
