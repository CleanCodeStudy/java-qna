package com.ccstudy.qna.service.dto.user;

import com.ccstudy.qna.domain.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequestDto {

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


    public User toEntity() {
        validPassword();
        return User.createBuilder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    private void validPassword() {
        if (!password.equals(confirmPassword)) {
            throw new RuntimeException();
        }
    }

}
