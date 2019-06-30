package com.ccstudy.qna.controller;

import com.ccstudy.qna.advice.common.BaseExceptionModelAndView;
import com.ccstudy.qna.dto.Account.AccountResDto;
import com.ccstudy.qna.dto.Account.AccountSaveReqDto;
import com.ccstudy.qna.dto.Account.AccountUpdateReqDto;
import com.ccstudy.qna.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@ComponentScan(basePackageClasses = BaseExceptionModelAndView.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    private AccountResDto accountResDto1;
    private AccountResDto accountResDto2;

    @Before
    public void setUp() throws Exception {
        accountResDto1 = AccountResDto.testBuilder()
                .id(1L)
                .email("testEmail@naver.com")
                .name("testsUserName")
                .userId("testUserId")
                .build();

        accountResDto2 = AccountResDto.testBuilder()
                .id(2L)
                .email("testEmail222@naver.com")
                .name("testsUserName222")
                .userId("testUserId222")
                .build();
    }

    @Test
    public void getAllAccounts_모든_계정_조회하는_페이지_리턴하기() throws Exception {
        //given
        Mockito.when(accountService.getAllAccounts())
                .thenReturn(new ArrayList<>(Arrays.asList(accountResDto1, accountResDto2)));
        //when
        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/users"))
                .andExpect(forwardedUrl("pages/users"))
                .andExpect(model().attribute("accounts", hasSize(2)))
                .andExpect(model().attribute("accounts", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("userId", is("testUserId")),
                                hasProperty("name", is("testsUserName")),
                                hasProperty("email", is("testEmail@naver.com"))
                        )
                )))
                .andExpect(model().attribute("accounts", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("userId", is("testUserId222")),
                                hasProperty("name", is("testsUserName222")),
                                hasProperty("email", is("testEmail222@naver.com"))
                        )
                )));
    }

    @Test
    public void 계정_수정하는_페이지_리턴() throws Exception {

        //given
        Mockito.when(accountService.findAccountById(1L))
                .thenReturn(accountResDto1);

        //when
        mvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/userUpdateForm"))
                .andExpect(forwardedUrl("pages/userUpdateForm"))
                .andExpect(model().attribute("account",
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("userId", is("testUserId")),
                                hasProperty("name", is("testsUserName")),
                                hasProperty("email", is("testEmail@naver.com"))
                        )
                ));

    }

    @Test
    public void 회원가입하기_이후에_redirection() throws Exception {
        //given
        AccountSaveReqDto accountSaveReqDto = AccountSaveReqDto.builder()
                .email("testEmail@naver.com")
                .name("testsUserName")
                .password("1234")
                .userId("testUserId")
                .build();

        Mockito.when(accountService.saveAccount(accountSaveReqDto))
                .thenReturn(1L);

        //when
        mvc.perform(post("/users"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/users"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void 계정_수정하기_후_redirection() throws Exception {
        //given
        AccountUpdateReqDto accountUpdateReqDto = AccountUpdateReqDto.builder()
                .email("testEmail@naver.com")
                .name("testsUserName")
                .changePassword("12345")
                .currentPassword("1234")
                .build();

        //when
        mvc.perform(put("/users/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/users"));
    }

    @Test
    public void loginAccount() {

    }
}