package com.ccstudy.qna.domain.repository;

import com.ccstudy.qna.domain.QQuestion;
import com.ccstudy.qna.domain.Question;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public QuestionRepositorySupport(JPAQueryFactory queryFactory) {
        super(Question.class);
        this.queryFactory = queryFactory;
    }

    public List<Question> findByTitle(String title) {
        return queryFactory.selectFrom(QQuestion.question)
                .where(QQuestion.question.title.eq(title))
                .fetch();
    }
}
