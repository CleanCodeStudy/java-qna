package com.ccstudy.qna.service.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@ToString
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginDto {

    @Email
    private String email;

    @NotBlank
    private String password;
}
