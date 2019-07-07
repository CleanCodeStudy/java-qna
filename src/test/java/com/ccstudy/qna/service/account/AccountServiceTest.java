package com.ccstudy.qna.service.account;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.account.AccountRepository;
import com.ccstudy.qna.dto.account.AccountLoginRequestDto;
import com.ccstudy.qna.dto.account.AccountSaveRequestDto;
import com.ccstudy.qna.dto.account.AccountUpdateRequestDto;
import com.ccstudy.qna.dto.account.LoginAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void 회원가입Dto가_account테이블에_저장() {
        //given
        AccountSaveRequestDto dto = AccountSaveRequestDto.createBuilder()
                .email("test@google.com")
                .firstName("홍")
                .lastName("길동")
                .password("1111")
                .confirmPassword("1111")
                .build();
        //when
        Long savedId = accountService.saveAccount(dto);

        //then
        Account account = accountRepository.findById(savedId).get();
        assertThat(account.getEmail(), is(dto.getEmail()));
        assertThat(account.getFirstName(), is(dto.getFirstName()));
        assertThat(account.getLastName(), is(dto.getLastName()));
        assertThat(account.getPassword(), is(dto.getPassword()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 이메일중복시_회원가입_불가() {
        //given
        AccountSaveRequestDto dto = AccountSaveRequestDto.createBuilder()
                .email("aaa@google.com")
                .firstName("홍")
                .lastName("길동")
                .password("1111")
                .confirmPassword("1111")
                .build();
        //when
        //then
        Long savedId = accountService.saveAccount(dto);
    }

    @Test
    public void 로그인_성공_테스트() {
        //given
        AccountLoginRequestDto dto = AccountLoginRequestDto.createBuilder()
                .email("bbb@google.com")
                .password("1234")
                .build();
        //when
        LoginAccount loginAccount = accountService.loginAccount(dto);
        //then
        assertThat(dto.getEmail(), is(loginAccount.getEmail()));
    }

    @Test(expected = IllegalStateException.class)
    public void 이메일이_존재하지않을때_로그인_실패() {
        //given
        AccountLoginRequestDto emailWrongDto = AccountLoginRequestDto.createBuilder()
                .email("aaa@aaaa.com")
                .password("1234")
                .build();
        //then
        LoginAccount emailWrondAccount = accountService.loginAccount(emailWrongDto);
    }

    @Test(expected = IllegalStateException.class)
    public void 비밀번호_틀린경우_로그인_실패() {
        //given
        AccountLoginRequestDto passwordWrongDto = AccountLoginRequestDto.createBuilder()
                .email("aaa@google.com")
                .password("111")
                .build();
        //when
        //then
        LoginAccount passwrodWrongAccount = accountService.loginAccount(passwordWrongDto);
    }

    @Test(expected = IllegalAccessError.class)
    public void find_by_id_실패() {
        //given
        Long id = 3L;
        //when
        //then
        accountService.findById(id);
    }

    @Test
    public void 사용자_전체_리스트_불러오기() {
        //given
        //when
        //then
        assertThat(accountService.findAll().size(), is(2));
    }

    @Test
    public void 회원수정Dto가_수정되는지() {
        //given
        Account account = accountRepository.findById(1L).get();
        AccountUpdateRequestDto dto = AccountUpdateRequestDto.updateBuilder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .afterPassword("1111")
                .confirmAfterPassword("1111")
                .currentPassword(account.getPassword())
                .build();
        //when
        Long updatedId = accountService.updateAccount(dto, 1L);

        //then
        Account findedaccount = accountRepository.findById(updatedId).get();
        assertThat(findedaccount.getFirstName(),is(dto.getFirstName()));
        assertThat(findedaccount.getLastName(),is(dto.getLastName()));
        assertThat(findedaccount.getPassword(), is("1111"));
    }
}

/*
controller만 , 통합테스트랑 두개
 */