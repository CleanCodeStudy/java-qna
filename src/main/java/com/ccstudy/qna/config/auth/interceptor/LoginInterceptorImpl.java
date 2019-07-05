package com.ccstudy.qna.config.auth.interceptor;

import com.ccstudy.qna.config.auth.Authentication;
import com.ccstudy.qna.dto.Account.AccountAuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptorImpl implements LoginInterceptor {

    private final Authentication authentication;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ModelMap modelMap = modelAndView.getModelMap();
        AccountAuthDto authDto= (AccountAuthDto)modelAndView.getModel()
                .get(AccountAuthDto.ATTRIBUTE_NAME);

        if(authDto==null){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

        if (modelMap.isEmpty()) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
        }
//        AccountAuthDto accountAuthDto = (AccountAuthDto) modelAndView.getModel()
//                .get(AccountAuthDto.ATTRIBUTE_NAME);
//        authentication.setAccountAuthDto(request, accountAuthDto);
//        modelAndView.addObject("id",accountAuthDto.getId());
    }

}
