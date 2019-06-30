package com.ccstudy.qna.web.auth.manager;

import com.ccstudy.qna.domain.entity.User;
import com.ccstudy.qna.domain.repository.UserRepository;
import com.ccstudy.qna.domain.support.AccessUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
public class AccessUserStubManager implements AccessUserManager {

    private static final String USER_SESSION_KEY = "StubUser";

    @Autowired
    private UserRepository repository;

    @Override
    public void saveAccessUser(HttpServletRequest request, AccessUser user) {
        log.info("{}님이 로그인 하였습니다.", user.getName());
        request.getSession().setAttribute(USER_SESSION_KEY, user);
    }

    @Override
    public AccessUser getAccessUser(HttpServletRequest request) {
        List<User> users = repository.findAll();

        if (users.isEmpty()) {
            return AccessUser.stubBuilder().id(1L)
                    .name("cys")
                    .email("chldbtjd2272@naver.com")
                    .build();
        }

        return new AccessUser(users.get(0));
    }
}
