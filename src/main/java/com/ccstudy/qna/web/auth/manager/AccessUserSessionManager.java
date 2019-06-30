package com.ccstudy.qna.web.auth.manager;

import com.ccstudy.qna.domain.support.AccessUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class AccessUserSessionManager implements AccessUserManager {

    private static final String USER_SESSION_KEY = "AccessUser";

    @Override
    public void saveAccessUser(HttpServletRequest request, AccessUser user) {
        log.info("{}님이 로그인 하였습니다.", user.getName());
        request.getSession().setAttribute(USER_SESSION_KEY, user);
    }

    @Override
    public AccessUser getAccessUser(HttpServletRequest request) {
        return (AccessUser) WebUtils.getSessionAttribute(request, USER_SESSION_KEY);
    }


}
