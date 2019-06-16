package com.ccstudy.qna.domain.repository;

import com.ccstudy.qna.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
