package com.ccstudy.qna.domain.repository;

import com.ccstudy.qna.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    // TODO : query로 del 아닌 것만 // query dsl : boolean function
    Optional<Question> findById(Long idx);

    @Query("SELECT q FROM Question q JOIN FETCH q.author")
    List<Question> findAllJoinFetch();
}