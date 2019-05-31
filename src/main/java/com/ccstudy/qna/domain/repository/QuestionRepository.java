package com.ccstudy.qna.domain.repository;

import com.ccstudy.qna.domain.entity.Question;

import java.util.List;

public interface QuestionRepository {
    void save(Question question);

    List<Question> findAll();
}
