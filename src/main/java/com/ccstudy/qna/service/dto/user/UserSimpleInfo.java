package com.ccstudy.qna.service.dto.user;

import com.ccstudy.qna.domain.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSimpleInfo {

    private Long id;
    private String email;
    private String name;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public UserSimpleInfo(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getUserName();
        this.createDate = user.getCreatedDateTime();
        this.modifyDate = user.getModifiedDateTime();
    }
}
