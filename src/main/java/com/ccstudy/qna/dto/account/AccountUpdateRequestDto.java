package com.ccstudy.qna.dto.account;

import com.ccstudy.qna.domain.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class AccountUpdateRequestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String currentPassword;
    private String afterPassword;


    @Builder
    public AccountUpdateRequestDto(Long id, String firstName, String lastName, String currentPassword, String afterPassword, String confirmAfterPassword) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentPassword = currentPassword;
        this.afterPassword = validationCheckPassword(afterPassword, confirmAfterPassword);
    }

    //TODO: 더 생각해보기
    public Account toEntiy(){
        return Account.builder()
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
