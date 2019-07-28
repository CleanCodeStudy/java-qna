package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.dto.Question.QuestionDetailResDto;
import com.ccstudy.qna.dto.Question.QuestionUpdateReqDto;
import com.ccstudy.qna.domain.repository.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Constructor;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {


    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    private Question question1;
    private Account account1;

    @Before
    public void setUp() throws Exception {
        account1 = Account.createBuilder()
                .userId("testId")
                .email("test@naver.com")
                .name("testName")
                .password("1234")
                .build();
        Constructor<Account> accountConstructor = Account.class.getDeclaredConstructor(Long.class, Account.class);
        accountConstructor.setAccessible(true);
        account1 = accountConstructor.newInstance(1L, account1);

        question1 = Question.createBuilder()
                .author(account1)
                .content("testContent")
                .title("testTitle")
                .build();
        Constructor<Question> testConstructor = Question.class.getDeclaredConstructor(Long.class, Question.class);
        testConstructor.setAccessible(true);
        question1 = testConstructor.newInstance(2L, question1);
    }


    @Test
    public void getQuestionBoard_Question_리스트_반환() {

        //when
        Mockito.when(questionRepository.findAllJoinFetch()).thenReturn(Collections.singletonList(question1));

        //then
        assertThat(questionService.getQuestionBoard().size()).isEqualTo(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void getQuestionDetail_없는_질문조회시_실패() {
        //given
        Long questionId = 1L;

        //when
        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        //then
        QuestionDetailResDto questionDetail = questionService.getQuestionDetail(1L);
    }

    @Test
    public void getQuestionDetail_성공() {
        //given
        Long questionId = 1L;

        //when
        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.of(question1));

        //then
        QuestionDetailResDto questionDetail = questionService.getQuestionDetail(1L);

        assertThat(questionDetail.getId()).isEqualTo(question1.getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void updateQuestion_없는_질문_수정시_실패() {
        //given
        Long questionId = 1L;

        QuestionUpdateReqDto questionUpdateReqDto = QuestionUpdateReqDto.builder()
                .title("updateTitle2")
                .content("updateContent")
                .build();

        //when
        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        //then
        questionService.updateQuestion(questionUpdateReqDto, 1L);
    }

    @Test
    public void updateQuestion_성공() {
        //given
        Long questionId = 1L;

        QuestionUpdateReqDto questionUpdateReqDto = QuestionUpdateReqDto.builder()
                .title("updateTitle2")
                .content("updateContent")
                .build();

        //when
        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.of(question1));

        //then
        questionService.updateQuestion(questionUpdateReqDto, 1L);

        assertThat(questionUpdateReqDto.getTitle()).isEqualTo(question1.getTitle());
        assertThat(questionUpdateReqDto.getContent()).isEqualTo(question1.getContent());
    }

}