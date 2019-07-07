package com.ccstudy.qna.domain.account;

import com.ccstudy.qna.domain.BaseTimeEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Account extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Setter
    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Setter
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Setter
    @Column(length = 20,nullable = false)
    private String password;

    @Builder(builderMethodName = "createBuilder")
    private Account(Long id, String email, String firstName, String lastName, String password) {
        Assert.notNull(email,"");
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public boolean isNotEqualPassword(String currentPassword) {
        return !StringUtils.equals(currentPassword, this.password);
    }

}
