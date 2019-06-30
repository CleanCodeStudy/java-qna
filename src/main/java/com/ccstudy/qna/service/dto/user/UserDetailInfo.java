package com.ccstudy.qna.service.dto.user;

import com.ccstudy.qna.domain.entity.User;
import lombok.Getter;

@Getter
public class UserDetailInfo {

    private Long id;
    private String email;
    private String name;


    public UserDetailInfo(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getUserName();
    }
}
