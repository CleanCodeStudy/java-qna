package com.ccstudy.qna.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserName {

    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return firstName + lastName;
    }
}
