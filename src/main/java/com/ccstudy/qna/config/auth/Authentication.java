package com.ccstudy.qna.config.auth;

import com.ccstudy.qna.dto.Account.AccountAuthDto;
import com.sun.deploy.net.HttpResponse;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface Authentication {

    Optional<AccountAuthDto> getAccountAuthDto(NativeWebRequest request);

    Optional<AccountAuthDto> getAccountAuthDto(HttpServletRequest request);

    void setAccountAuthDto(HttpServletRequest request, AccountAuthDto accountAuthDto);

    void removeAccountAuthDto(HttpServletRequest request);

    void updateAccountExpireTimeAuthDto(HttpServletRequest request);
}
