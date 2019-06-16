package com.ccstudy.qna.service.dto.user;

import com.ccstudy.qna.domain.entity.User;
import lombok.Getter;

@Getter
public class UserDetailInfo {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;


    public UserDetailInfo(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}
