package com.ccstudy.qna.web.controller.answer;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.account.AccountRepository;
import com.ccstudy.qna.domain.answer.AnswerRepository;
import com.ccstudy.qna.domain.question.Question;
import com.ccstudy.qna.domain.question.QuestionRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnswerControllerTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mock;

    private Account account;

    private Question question;

    @Before
    public void setUp() throws Exception {
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
        account = getAccount();
        question = saveQuestion();
    }

    @After
    public void tearDown() throws Exception {
        answerRepository.deleteAll();
    }

    @Test
    public void 답변_작성_성공() {
        //given
        //when
        //then
    }

    @Test
    public void 답변_하나_조회() {
        //given
        //when
        //then
    }

    @Test
    public void 답변_삭제_성공() {
        //given
        //when
        //then
    }

    private Account getAccount() {
        return accountRepository.findAccountByEmail("aaa@google.com")
                .orElseThrow(NoSuchFieldError::new);
    }

    private Question saveQuestion() {
        return questionRepository.save(createQuestion(getAccount()));
    }

    private Question createQuestion(Account account) {
        return Question.createBuilder()
                .author(account)
                .title("title1")
                .contents("title2")
                .build();
    }
}