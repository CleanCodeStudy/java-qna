package com.ccstudy.qna.domain.repository;

import com.ccstudy.qna.domain.entity.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionDummyRepository implements QuestionRepository {
    private Map<String,Question> questions = new HashMap<>();

    @Override
    public void save(Question question) {
        if(questions.containsValue(question.getId())){
            throw new RuntimeException();
        }

        questions.put(question.getId(),question);
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(questions.values());
    }
}
