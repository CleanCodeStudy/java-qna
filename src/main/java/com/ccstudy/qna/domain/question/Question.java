package com.ccstudy.qna.domain.question;

import com.ccstudy.qna.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Question extends BaseTimeEntity {

    private String title;
    private String author;
    private String contents;

    @Builder
    public Question(String title, String author, String contents) {
        this.title = title;
        this.author = author;
        this.contents = contents;
    }

    //modify


}
