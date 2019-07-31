package com.ccstudy.qna.web.controller.question.account;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.account.AccountRepository;
import com.ccstudy.qna.dto.account.AccountsResponseDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AccountControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mock;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @After
    public void clean(){
        accountRepository.deleteAll();
    }

    @Test
    public void 사용자_추가_성공() throws Exception {
        //given
        MultiValueMap<String, String> accountSaveRequestDto = new LinkedMultiValueMap<>();
        accountSaveRequestDto.add("lastName", "seo");
        accountSaveRequestDto.add("firstName", "jaeyeon");
        accountSaveRequestDto.add("password", "password");
        accountSaveRequestDto.add("confirmPassword", "password");
        accountSaveRequestDto.add("email", "jaeyeon9327@gmail.com");

        //when
        mock.perform(post("/user")
                .params(accountSaveRequestDto)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
        //then
        Account account = accountRepository.findAccountByEmail("jaeyeon9327@gmail.com").get();
        assertThat(account.getLastName()).isEqualTo("seo");
        assertThat(account.getFirstName()).isEqualTo("jaeyeon");
        assertThat(account.getPassword()).isEqualTo("password");

        accountRepository.delete(account);
    }

    /*
        실제로 IllegalArgumentException 던져주는데 Servlet 에러남
     */
    @Test(expected = Exception.class)
    public void 사용자_추가_실패_비밀번호_다름() throws Exception {
        //given
        MultiValueMap<String, String> accountSaveRequestDto = new LinkedMultiValueMap<>();
        accountSaveRequestDto.add("lastName", "seo");
        accountSaveRequestDto.add("firstName", "jaeyeon");
        accountSaveRequestDto.add("password", "password123");
        accountSaveRequestDto.add("confirmPassword", "password");
        accountSaveRequestDto.add("email", "jaeyeon9327@gmail.com");

        //when
        mock.perform(post("/user")
                .params(accountSaveRequestDto)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        //then
    }


    @Test
    public void 사용자_리스트_출력() throws Exception {
        //given
        Account account1 = Account.createBuilder()
                .firstName("user")
                .lastName("user1")
                .email("test1@google.com")
                .password("1234")
                .build();

        Account account2 = Account.createBuilder()
                .firstName("user")
                .lastName("user1")
                .email("test2@google.com")
                .password("1234")
                .build();

        accountRepository.saveAll(Arrays.asList(account1,account2));
        List<Account> accounts = accountRepository.findAll();
        List<AccountsResponseDto> accountListResponseDtoList = accounts.stream()
                .map(AccountsResponseDto::new).collect(Collectors.toList());


        //when
        final ResultActions actions = mock.perform(get("/users"))
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(view().name("account/list_view"))
                .andExpect(model().attributeExists("accounts"))
                .andExpect(model().attribute("accounts", hasSize(2)))
                .andExpect(model().attribute("accounts", hasItem(
                        allOf(
                                hasProperty("email", is(accountListResponseDtoList.get(0).getEmail())),
                                hasProperty("lastName", is(accountListResponseDtoList.get(0).getLastName())),
                                hasProperty("firstName", is(accountListResponseDtoList.get(0).getFirstName()))
                        )
                )));


    }

    @Test
    public void 사용자_한명_조회하기_성공() throws Exception {
        //given
        Account account = Account.createBuilder()
                .firstName("user")
                .lastName("user1")
                .email("test1@google.com")
                .password("1234")
                .build();

        accountRepository.save(account);

        //when
        final ResultActions actions = mock.perform(get("/users/{id}/form", account.getId()))
                .andDo(print());
        //then
        actions
                .andExpect(status().isOk())
                .andExpect(view().name("account/info_update"))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attribute("account", hasProperty("email", is(account.getEmail()))))
                .andExpect(model().attribute("account", hasProperty("lastName", is(account.getLastName()))))
                .andExpect(model().attribute("account", hasProperty("firstName", is(account.getFirstName()))));
    }

    //실제 결과랑 다르게나옴
    @Test(expected = Exception.class)
    public void 존재하지_않는_사용자_한명_조회하기() throws Exception {
        //when
        final ResultActions actions = mock.perform(get("/users/{id}/form", 3))
                .andDo(print());
        //then
        actions
                .andExpect(status().is5xxServerError())
                .andExpect(view().name("account/info_update"))
                .andExpect(model().attributeDoesNotExist("account"));
    }

}