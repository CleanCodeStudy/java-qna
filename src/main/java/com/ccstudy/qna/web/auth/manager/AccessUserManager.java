package com.ccstudy.qna.web.auth.manager;

import com.ccstudy.qna.domain.support.AccessUser;

import javax.servlet.http.HttpServletRequest;

public interface AccessUserManager {

    void saveAccessUser(HttpServletRequest request, AccessUser user);

    AccessUser getAccessUser(HttpServletRequest request);
}
