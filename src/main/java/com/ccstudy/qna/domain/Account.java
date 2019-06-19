package com.ccstudy.qna.domain;

import com.ccstudy.qna.exception.ChangePasswordException;
import com.ccstudy.qna.exception.CheckPasswordException;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;

    @Setter
    private String email;

    @Setter
    private String password;

    @Setter
    private String name;

    @Builder(builderMethodName = "createBuilder")
    private Account(String userId, String email, String password, String name) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    private Account(Long id, Account account) {
        this.id = id;
        this.userId = account.getUserId();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.name = account.getName();
    }

    public void validatePassword(String checkPassword, String changePassword) {
        validateCurrentPassword(checkPassword);
        validateDifferentPassword(changePassword);
    }

    public void validateCurrentPassword(String checkPassword) {
        if (!checkPassword.equals(password)) {
            throw new CheckPasswordException("입력한 패스워드 값이 현재 패스워드 값과 같지 않습니다.");
        }
    }

    private void validateDifferentPassword(String changePassword) {
        if (changePassword.equals(password)) {
            throw new ChangePasswordException("현재 패스워드와 바꾸려는 패스워드 값이 같습니다");
        }
    }
}
