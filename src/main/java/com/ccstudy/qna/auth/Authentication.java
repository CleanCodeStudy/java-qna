package com.ccstudy.qna.auth;

import com.ccstudy.qna.dto.account.LoginAccount;
import org.springframework.web.context.request.NativeWebRequest;

public interface Authentication {
    LoginAccount getLoginUser(NativeWebRequest nativeWebRequest) throws Exception;
}
