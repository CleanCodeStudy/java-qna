package com.ccstudy.qna.service.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateDto {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

}
