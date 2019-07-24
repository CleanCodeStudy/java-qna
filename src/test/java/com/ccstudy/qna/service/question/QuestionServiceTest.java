package com.ccstudy.qna.service.question;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.account.AccountRepository;
import com.ccstudy.qna.domain.question.Question;
import com.ccstudy.qna.domain.question.QuestionRepository;
import com.ccstudy.qna.dto.account.LoginAccount;
import com.ccstudy.qna.dto.question.QuestionDetailResponseDto;
import com.ccstudy.qna.dto.question.QuestionSaveRequestDto;
import com.ccstudy.qna.dto.question.QuestionUpdateRequestDto;
import com.ccstudy.qna.error.UnAuthenticationException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class QuestionServiceTest {

    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AccountRepository accountRepository;

    @After
    public void clean(){
        questionRepository.deleteAll();
        accountRepository.deleteAll();
    }
    @Test
    public void 정상적으로_저장되는지() {
        //given
        Account account = Account.createBuilder()
                .lastName("seo")
                .firstName("jaeyeon")
                .email("a1010100z@naver.com")
                .password("1234")
                .build();
        accountRepository.save(account);

        QuestionSaveRequestDto questionSaveRequestDto = QuestionSaveRequestDto.createBuilder()
                .title("title")
                .contents("contents")
                .build();

        LoginAccount loginAccount = LoginAccount.createBuilder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .build();
        //when
        long id = questionService.save(questionSaveRequestDto, loginAccount);
        //then
        Question question = questionRepository.findById(id).get();
        assertThat(question.getAuthor().getEmail(), is(account.getEmail()));
        assertThat(question.getContents(), is("contents"));
        assertThat(question.getTitle(), is("title"));
    }

    @Test
    public void 질문_상세보기_성공() {
        //given
        long id = saveQuestion();
        //when
        QuestionDetailResponseDto questionDetailResponseDto = questionService.showQuestionDetail(id);
        //then
        Question question = questionRepository.findById(id).get();
        assertThat(questionDetailResponseDto.getTitle(), is(question.getTitle()));
        assertThat(questionDetailResponseDto.getContents(), is(question.getContents()));
    }

    @Test
    public void 질문_수정_성공() {
        //given
        long id = saveQuestion();
        //when
        QuestionUpdateRequestDto questionUpdateRequestDto = QuestionUpdateRequestDto.builder()
                .title("Change")
                .id(id)
                .build();


        LoginAccount loginAccount = LoginAccount.createBuilder()
                .firstName("seo")
                .lastName("jaeyeon")
                .email("a1010100z@naver.com")
                .build();

        questionService.updateQuestion(questionUpdateRequestDto, loginAccount);
        //then
        Question question = questionRepository.findById(id).get();
        assertThat(question.getTitle(), is("Change"));
    }

    @Test(expected = UnAuthenticationException.class)
    public void 질문_수정_실패_작성자_불일치(){
        //given
        long id = saveQuestion();
        //when
        QuestionUpdateRequestDto questionUpdateRequestDto = QuestionUpdateRequestDto.builder()
                .title("Change")
                .id(id)
                .build();


        LoginAccount loginAccount = LoginAccount.createBuilder()
                .firstName("lee")
                .lastName("ln")
                .email("test@google.com")
                .build();

        questionService.updateQuestion(questionUpdateRequestDto, loginAccount);
        //then
    }
    @Test
    public void delete() {
        //given
        long id = saveQuestion();

        Question question = questionRepository.findById(id).get();
        //then
        questionRepository.delete(question);
        assertThat(questionRepository.findById(id), is(Optional.empty()));
    }

    private long saveQuestion(){

        //given
        Account account = Account.createBuilder()
                .lastName("seo")
                .firstName("jaeyeon")
                .email("a1010100z@naver.com")
                .password("1234")
                .build();
        accountRepository.save(account);

        QuestionSaveRequestDto questionSaveRequestDto = QuestionSaveRequestDto.createBuilder()
                .title("title")
                .contents("contents")
                .build();

        LoginAccount loginAccount = LoginAccount.createBuilder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .build();

        return questionService.save(questionSaveRequestDto, loginAccount);

    }
}