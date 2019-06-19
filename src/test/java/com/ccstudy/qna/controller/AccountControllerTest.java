package com.ccstudy.qna.controller;

import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.dto.Account.AccountResDto;
import com.ccstudy.qna.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebMvcTest(AccountController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountControllerTest {

    protected MockMvc mvc;
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
    public void 모든_계정_조회하는_페이지_리턴하기() throws Exception {
        //given
        Mockito.when(accountService.getAllAccounts())
                .thenReturn(new ArrayList<>(Arrays.asList(accountResDto1, accountResDto2)));
        //when
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/index"))
                .andExpect(forwardedUrl("/pages/index.hbs"))
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
    public void getEditFormOfAccount() {
    }

    @Test
    public void saveAccount() {
    }

    @Test
    public void updateAccount() {
    }

    @Test
    public void loginAccount() {
    }
}