package com.ccstudy.qna.domain.repository;

import com.ccstudy.qna.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findById(Long id);

    Optional<Account> findByUserId(String userId);
}
