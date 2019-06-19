package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.dto.Question.QuestionDetailResDto;
import com.ccstudy.qna.dto.Question.QuestionUpdateReqDto;
import com.ccstudy.qna.repository.QuestionRepository;
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
public class QuestionServiceTest {


    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    private Question question1;

    @Before
    public void setUp() throws Exception {
        question1 = Question.createBuilder()
                .author("testAuthor")
                .content("testContent")
                .title("testTitle")
                .build();
        Constructor<Question> testConstructor = Question.class.getDeclaredConstructor(Long.class, Question.class);
        testConstructor.setAccessible(true);
        question1 = testConstructor.newInstance(2L, question1);
    }


    @Test
    public void getQuestionBoard_Question_리스트_반환() {
        //given

        //when
        Mockito.when(questionRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(question1)));

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
                .author("updateAuthor2")
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
                .author("updateAuthor2")
                .title("updateTitle2")
                .content("updateContent")
                .build();

        //when
        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.of(question1));

        //then
        questionService.updateQuestion(questionUpdateReqDto, 1L);

        assertThat(questionUpdateReqDto.getAuthor()).isEqualTo(question1.getAuthor());
        assertThat(questionUpdateReqDto.getTitle()).isEqualTo(question1.getTitle());
        assertThat(questionUpdateReqDto.getContent()).isEqualTo(question1.getContent());
    }

}