package com.ccstudy.qna.controller;

import com.ccstudy.qna.advice.common.BaseExceptionModelAndView;
import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.domain.repository.AccountRepository;
import com.ccstudy.qna.dto.Account.AccountSaveReqDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@ComponentScan(basePackageClasses = BaseExceptionModelAndView.class)
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional
    public void saveAccount_정상저장_확인하기() throws Exception {
        //given
        String userId = "pci";
        String password = "1234";
        String name = "chanin";
        String email = "example@gmail.com";

        //when
        this.mockMvc.perform(post("/users")
                .param("userId", userId)
                .param("password", password)
                .param("name", name)
                .param("email", email)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();

        //then
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(RuntimeException::new);

        assertThat(account.getUserId()).isEqualTo(userId);
        assertThat(account.getEmail()).isEqualTo(email);
        assertThat(account.getName()).isEqualTo(name);
        assertThat(account.getPassword()).isEqualTo(password);
    }

    @Test
    @Transactional
    public void updateAccount_정상수정_확인하기() throws Exception {
        //given
        String userId = "pci";
        String password = "1234";
        String name = "chanin";
        String email = "example@gmail.com";
        AccountSaveReqDto accountSaveReqDto = AccountSaveReqDto.builder()
                .email(email)
                .name(name)
                .password(password)
                .userId(userId)
                .build();
        Account saveAccount = accountSaveReqDto.toEntity();
        saveAccount = accountRepository.save(saveAccount);

        Long id = saveAccount.getId();

        String changeName = "chan";
        String changeEmail = "xample@gmail.com";
        String changePassword = "123";

        //when
        this.mockMvc.perform(put("/users/{id}", id)
                .param("currentPassword", password)
                .param("changePassword", changePassword)
                .param("name", changeName)
                .param("email", changeEmail)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();

        //then
        Account account = accountRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        assertThat(account.getPassword()).isEqualTo(changePassword);
        assertThat(account.getName()).isEqualTo(changeName);
        assertThat(account.getEmail()).isEqualTo(changeEmail);
    }

}