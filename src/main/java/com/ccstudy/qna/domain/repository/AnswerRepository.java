package com.ccstudy.qna.domain.repository;

import com.ccstudy.qna.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    //    @Query("SELECT q FROM Question q JOIN FETCH q.author")
    Optional<Answer> findByQuestionId(Long idx);
}
