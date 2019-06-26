package com.ccstudy.qna.interceptor;

import com.ccstudy.qna.dto.Account.AccountSessionDto;
import com.ccstudy.qna.exception.SessionMismatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Profile("prod")
@Component
@Slf4j
public class LoginSessionCheckInterceptorImpl implements LoginSessionCheckInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AccountSessionDto accountSessionDto = (AccountSessionDto) request.getSession()
                .getAttribute(AccountSessionDto.ATTRIBUTE_NAME);
        Long pathId = getPathId(request);
        validateId(accountSessionDto, pathId);
        return true;
    }

    private Long getPathId(HttpServletRequest request) {
        Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return Long.parseLong((String) pathVariables.get("id"));
    }

    private void validateId(AccountSessionDto sessionDto, Long pathId) {
        if (!sessionDto.getId().equals(pathId)) {
            throw new SessionMismatchException();
        }
    }
}
