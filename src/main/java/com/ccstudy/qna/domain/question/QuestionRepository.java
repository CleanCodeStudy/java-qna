package com.ccstudy.qna.domain.question;

import java.util.List;

public interface QuestionRepository {
    int save(Question question);
    List<Question> getQuestionList();
    Question getQuestionDetail(int index);
}
