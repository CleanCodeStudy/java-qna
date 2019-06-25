package com.ccstudy.qna.dto.Account;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountLoginReqDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;


}
