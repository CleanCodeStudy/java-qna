package com.ccstudy.qna.domain.question;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository{ // interface로 빼두고 구현체로

    private List<Question> questionList = new ArrayList<>();

    public int save(Question question) {
        questionList.add(question);
        return questionList.size();
    }

    public List<Question> getQuestionList() {
        return new ArrayList<>(questionList);
    }

    public Question getQuestionDetail(int index) {
        return questionList.get(index);
    }

}

