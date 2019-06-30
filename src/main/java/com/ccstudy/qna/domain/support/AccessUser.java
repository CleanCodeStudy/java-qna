package com.ccstudy.qna.domain.support;

import com.ccstudy.qna.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccessUser {

    private Long id;
    private String name;
    private String email;


    public AccessUser(User user) {
        this.id = user.getId();
        this.name = user.getUserName();
        this.email = user.getEmail();
    }

    @Builder(builderMethodName = "stubBuilder")
    private AccessUser(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
