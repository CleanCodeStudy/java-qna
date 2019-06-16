package com.ccstudy.qna.dto.account;

import com.ccstudy.qna.domain.account.Account;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountUpdateRequestDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String afterPassword;


    @Builder(builderMethodName = "updateBuilder")
    public AccountUpdateRequestDto(String firstName, String lastName, String currentPassword, String afterPassword, String confirmAfterPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentPassword = currentPassword;
        this.afterPassword = validationCheckPassword(afterPassword, confirmAfterPassword);
    }

    public Account toEntiy(){
        return Account.createBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .password(afterPassword)
                .build();
    }

    public String validationCheckPassword(String password, String confirmPassword){
        if(!StringUtils.equals(password,confirmPassword)){
            throw new RuntimeException();
        }
        return password;
    }
}
