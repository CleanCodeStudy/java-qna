package com.ccstudy.qna.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;

//    @JoinColumn
//    @ManyToOne
//    private User user;

    @Builder(builderMethodName = "createBuilder")
    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}

