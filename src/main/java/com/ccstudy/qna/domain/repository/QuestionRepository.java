package com.ccstudy.qna.domain.repository;

import com.ccstudy.qna.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
