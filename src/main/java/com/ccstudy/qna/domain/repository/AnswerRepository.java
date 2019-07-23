package com.ccstudy.qna.domain.repository;

import com.ccstudy.qna.domain.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
