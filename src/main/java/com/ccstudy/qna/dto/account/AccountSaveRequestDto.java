package com.ccstudy.qna.dto.account;

import com.ccstudy.qna.domain.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class AccountSaveRequestDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    @Builder
    public AccountSaveRequestDto(String email, String firstName, String lastName, String password, String confirmPassword) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = validationCheckPassword(password,confirmPassword);
    }

    public Account toEntity(){
        return Account.builder()
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
