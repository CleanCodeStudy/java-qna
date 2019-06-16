package com.ccstudy.qna.config;

import com.ccstudy.qna.domain.entity.User;
import com.ccstudy.qna.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitComponent implements ApplicationRunner {

    private final UserRepository repository;


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        repository.save(User.createBuilder()
                .email("chldbtjd2272@naver.com")
                .firstName("choi")
                .lastName("ys")
                .password("qweqweqwe")
                .build());
    }
}
