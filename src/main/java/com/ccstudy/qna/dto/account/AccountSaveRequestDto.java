package com.ccstudy.qna.dto.account;

import com.ccstudy.qna.domain.account.Account;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class AccountSaveRequestDto {

    @Email
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    @Builder(builderMethodName = "createBuilder")
    public AccountSaveRequestDto(String email, String firstName, String lastName, String password, String confirmPassword) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = validationCheckPassword(password,confirmPassword);
    }

    public Account toEntity(){
        return Account.createBuilder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .build();
    }

    public String validationCheckPassword(String password, String confirmPassword){
        if(!StringUtils.equals(password,confirmPassword)){
            throw new RuntimeException();
        }
        return password;
    }
}
