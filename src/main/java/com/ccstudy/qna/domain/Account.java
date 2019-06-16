package com.ccstudy.qna.domain;

import com.ccstudy.qna.exception.PasswordException;
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

    @Builder
    public Account(String userId, String email, String password, String name) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void validatePassword(String checkPassword, String changePassword) {
        validateIsCurrentPassword(checkPassword);
        validateDifferentPassword(changePassword);
    }

    private void validateIsCurrentPassword(String checkPassword) {
        if (!checkPassword.equals(password)) {
            throw new PasswordException("입력한 패스워드 값이 현재 패스워드 값과 같지 않습니다.");
        }
    }

    private void validateDifferentPassword(String changePassword) {
        if (changePassword.equals(password)) {
            throw new PasswordException("현재 패스워드와 바꾸려는 패스워드 값이 같습니다");
        }
    }
}
