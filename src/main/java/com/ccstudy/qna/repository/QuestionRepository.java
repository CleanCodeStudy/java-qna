package com.ccstudy.qna.repository;

import com.ccstudy.qna.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {
    private List<Question> questions = new ArrayList<>();

    public List<Question> findAllQuestion(){
        return questions;
    }

    public void saveQuestion(Question question){
        this.questions.add(question);
    }

}