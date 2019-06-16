package com.ccstudy.qna.service.account;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.account.AccountRepository;
import com.ccstudy.qna.dto.account.AccountSaveRequestDto;
import com.ccstudy.qna.dto.account.AccountUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @After
    public void cleanUp() throws Exception {
        accountRepository.deleteAll();
    }

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
        assertThat(account.getEmail(),is(dto.getEmail()));
        assertThat(account.getFirstName(),is(dto.getFirstName()));
        assertThat(account.getLastName(),is(dto.getLastName()));
        assertThat(account.getPassword(),is(dto.getPassword()));
    }

    @Test
    public void 회원수정Dto가_수정되는지() {
        /*
        ('aaa@google.com','fn','ln','1234');
        */

        //given
        Account account = accountRepository.findById(new Long(1)).get();
        AccountUpdateRequestDto dto = AccountUpdateRequestDto.updateBuilder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .afterPassword("1111")
                .confirmAfterPassword("1111")
                .currentPassword(account.getPassword())
                .build();
     /*   //when
        Long updatedId = accountService.updateAccount(dto);

        //then
        Account findedaccount=accountRepository.findById(updatedId).get();
        assertThat(findedaccount.getFirstName(),is(dto.getFirstName()));
        assertThat(findedaccount.getLastName(),is(dto.getLastName()));
        assertThat(findedaccount.getPassword(),is("1111"));*/
    }
}

/*
controller만 , 통합테스트랑 두개
 */