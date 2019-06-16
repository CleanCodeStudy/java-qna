package com.ccstudy.qna.service.converter;

import com.ccstudy.qna.domain.entity.User;
import com.ccstudy.qna.service.dto.user.UserUpdateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateConverter {

    public static User update(User user, UserUpdateDto updateDto) {
        user.setEmail(updateDto.getEmail());
        user.setFirstName(updateDto.getFirstName());
        user.setLastName(updateDto.getLastName());
        user.setPassword(updateDto.getPassword());
        return user;
    }

}
