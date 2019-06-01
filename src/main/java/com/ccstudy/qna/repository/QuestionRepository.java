package com.ccstudy.qna.repository;

import com.ccstudy.qna.domain.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepository {
    private List<Question> questions = new ArrayList<>();

    public List<Question> findAllQuestion() {
        return new ArrayList<>(questions);
    }

    public void saveQuestion(Question question) {
        this.questions.add(question);
    }

    public Question findQuestionById(Long index) {
        return questions.get(Integer.valueOf(index.toString()));
    }


}