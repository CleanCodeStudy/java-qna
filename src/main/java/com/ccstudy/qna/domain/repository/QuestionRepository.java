package com.ccstudy.qna.domain.repository;

import com.ccstudy.qna.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question q join fetch q.user")
    List<Question> findAllJoinFetch();

    @Modifying
    @Transactional
    @Query("delete from Question")
    void deleteAllFetch();
}
