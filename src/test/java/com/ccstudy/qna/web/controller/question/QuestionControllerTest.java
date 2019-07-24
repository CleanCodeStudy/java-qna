package com.ccstudy.qna.web.controller.question;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.account.AccountRepository;
import com.ccstudy.qna.domain.question.Question;
import com.ccstudy.qna.domain.question.QuestionRepository;
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
import org.springframework.web.context.WebApplicationContext;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mock;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Account account;

    @Before
    public void setUp(){
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
        //saveAccount();
        account = accountRepository.findAccountByEmail("aaa@google.com")
                .orElseThrow(NoSuchElementException::new);
    }

    @After
    public void tearDown() throws Exception {
        questionRepository.deleteAll();
    }


    //질문 작성 성공
    @Test
    public void 질문_한개_작성_성공() throws Exception {
        //given
        //when
        mock.perform(post("/questions")
                .param("title", "title1")
                .param("contents", "contents1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
        //then
        Question question = questionRepository.findAll().get(0);
        assertThat(question.getTitle()).isEqualTo("title1");
        assertThat(question.getContents()).isEqualTo("contents1");
    }

    //질문 하나 조회
    @Test
    public void 질문_한개_조회_성공() throws Exception {
        //given
        Long id = saveQuestion(account);
        //when
        final ResultActions actions = mock.perform(get("/questions/{id}", id))
                .andDo(print());
        //then
        actions.andExpect(status().isOk())
                .andExpect(view().name("question/detail_view"))
                .andExpect(model().attributeExists("question"))
                .andExpect(model().attribute("question", hasProperty("title", is("title1"))))
                .andExpect(model().attribute("question", hasProperty("contents", is("contents1"))));
    }

    //질문 수정
    @Test
    public void 질문_수정_성공() throws Exception {
        //given
        Long id = saveQuestion(account);
        //when
        mock.perform(put("/questions/{id}", id)
                .param("id", id.toString())
                .param("title", "title2")
                .param("contents", "contents2")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
        //then
        Question question = questionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        assertThat(questionRepository.findAll().size()).isEqualTo(1);
        assertThat(question.getTitle()).isEqualTo("title2");
        assertThat(question.getContents()).isEqualTo("contents2");
    }

    //TODO: Web에서 튕겨주는건 어떻게 처리???
//    @Test
//    public void 본인_질문이_아니여서_수정_실패() throws Exception {
//        //given
//        Account wrongAccount = accountRepository.findAccountByEmail("bbb@google.com")
//                .orElseThrow(NoSuchElementException::new);
//        saveQuestion(wrongAccount);
//        //when
//        mock.perform(put("/questions/{id}",1)
//                .param("id","1")
//                .param("title","title2")
//                .param("contents","contents2")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andReturn();
//        //then
//    }

    //질문 삭제
    @Test
    public void 질문_삭제_성공() throws Exception {
        //given
        Long id = saveQuestion(account);
        int beforeSize = questionRepository.findAll().size();
        //when
        mock.perform(delete("/questions/{id}", id)
                .param("email", account.getEmail())
                .param("firstName", account.getFirstName())
                .param("lastName", account.getLastName())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
        //then
        assertThat(questionRepository.findAll().size()).isEqualTo(beforeSize - 1);
    }

    private void saveAccount() {
        accountRepository.save(createAccount());
    }

    private Account createAccount() {
        return Account.createBuilder()
                .email("aaa@google.com")
                .firstName("fn")
                .lastName("ln")
                .password("1234")
                .build();
    }

    private Long saveQuestion(Account account) {
        return questionRepository.save(createQuestion(account)).getId();
    }

    private Question createQuestion(Account account) {
        return Question.createBuilder()
                .author(account)
                .title("title1")
                .contents("contents1")
                .build();
    }
}