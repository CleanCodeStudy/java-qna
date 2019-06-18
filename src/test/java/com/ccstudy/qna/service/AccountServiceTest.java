package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.dto.Account.AccountResDto;
import com.ccstudy.qna.dto.Account.AccountSaveReqDto;
import com.ccstudy.qna.dto.Account.AccountUpdateReqDto;
import com.ccstudy.qna.repository.AccountRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    private Account account1;

    @Before
    public void setUp() throws Exception {
        account1 = Account.createBuilder()
                .userId("testId")
                .email("test@naver.com")
                .name("testName")
                .password("1234")
                .build();
        Constructor<Account> testConstructor = Account.class.getDeclaredConstructor(Long.class, Account.class);
        testConstructor.setAccessible(true);
        account1 = testConstructor.newInstance(2L, account1);
    }

    @Test
    public void getAllAccounts_기존2개_반환() {
        //given

        Account account2 = Account.createBuilder()
                .userId("testId")
                .email("test@naver.com")
                .name("testName")
                .password("1234")
                .build();

        //when
        Mockito.when(accountRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(account1, account2)));

        //then
        assertThat(accountService.getAllAccounts().size()).isEqualTo(2);
    }

    @Test
    public void saveAccount_정상_저장() {
        //given
        AccountSaveReqDto saveReqDto = AccountSaveReqDto.builder()
                .email("test@naver.com")
                .name("testName")
                .password("1234")
                .userId("testId")
                .build();

        //when
        Mockito.when(accountRepository.findByUserId(saveReqDto.getUserId())).thenReturn(Optional.empty());

        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account1);

        //then
        Long accountId = accountService.saveAccount(saveReqDto);

        assertThat(accountId).isEqualTo(2);
    }

    @Test(expected = RuntimeException.class)
    public void saveAccount_이메일_중복으로_저장실패() {
        //given
        AccountSaveReqDto saveReqDto = AccountSaveReqDto.builder()
                .email("test@naver.com")
                .name("testName")
                .password("1234")
                .userId("testId")
                .build();

        //when
        Mockito.when(accountRepository.findByUserId(saveReqDto.getUserId())).thenReturn(Optional.of(account1));

        //then
        accountService.saveAccount(saveReqDto);
    }

    @Test
    public void findAccountById_성공() {
        //given
        Long accountId = 2L;

        //when
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.of(account1));

        //then
        AccountResDto resultAccount = accountService.findAccountById(accountId);

        assertThat(resultAccount).isNotNull();
    }

    @Test(expected = NoSuchElementException.class)
    public void findAccountById_실패() {
        //given
        Long accountId = 2L;

        //when
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        //then
        AccountResDto resultAccount = accountService.findAccountById(accountId);
    }

    @Test(expected = NoSuchElementException.class)
    public void updateAccount_없는유저가_수정하려할때() {
        //given
        Long accountId = 2L;

        AccountUpdateReqDto accountUpdateReqDto = AccountUpdateReqDto.builder()
                .changePassword("1234")
                .currentPassword("1234")
                .email("hello@naver.com")
                .name("nameName")
                .build();

        //when
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        //then
        accountService.updateAccount(accountUpdateReqDto, accountId);
    }

    @Test
    public void updateAccount_수정_성공() {
        //given
        Long accountId = 2L;

        AccountUpdateReqDto accountUpdateReqDto = AccountUpdateReqDto.builder()
                .changePassword("12345678")
                .currentPassword("1234")
                .email("hello@naver.com")
                .name("nameName")
                .build();

        //when
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.of(account1));

        //then
        accountService.updateAccount(accountUpdateReqDto, accountId);

        assertThat(account1.getEmail()).isEqualTo("hello@naver.com");
    }
}