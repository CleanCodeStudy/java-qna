package com.ccstudy.qna.controller;

import com.ccstudy.qna.advice.common.BaseExceptionModelAndView;
import com.ccstudy.qna.dto.Question.QuestionDetailResDto;
import com.ccstudy.qna.dto.Question.QuestionReqDto;
import com.ccstudy.qna.dto.Question.QuestionResDto;
import com.ccstudy.qna.dto.Question.QuestionUpdateReqDto;
import com.ccstudy.qna.service.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@ComponentScan(basePackageClasses = BaseExceptionModelAndView.class)
@ComponentScan("com.ccstudy.qna.interceptor")
public class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionService questionService;

    private QuestionResDto questionResDto1;
    private QuestionResDto questionResDto2;
    private QuestionDetailResDto questionDetailResDto;

    @Before
    public void setUp() throws Exception {

        questionResDto1 = QuestionResDto.testBuilder()
                .id(1L)
                .author("testUserId")
                .title("titleTest1")
                .registerDate("2019-06-26 17:00")
                .updateDate("2019-06-26 17:00")
                .build();

        questionResDto2 = QuestionResDto.testBuilder()
                .id(2L)
                .author("testUserId222")
                .title("titleTest2")
                .registerDate("2019-06-26 18:00")
                .updateDate("2019-06-26 18:00")
                .build();

        questionDetailResDto = QuestionDetailResDto.testBuilder()
                .id(1L)
                .author("testUserId")
                .title("titleTest1")
                .content("contentTest1")
                .registerDate("2019-06-26 17:00")
                .updateDate("2019-06-26 17:00")
                .build();
    }


    @Test
    public void getQuestionBoard_모든_질문_조회하는_페이지_리턴하기() throws Exception {
        //given
        Mockito.when(questionService.getQuestionBoard())
                .thenReturn(new ArrayList<>(Arrays.asList(questionResDto1, questionResDto2)));
        //when
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/pages/index"))
                .andExpect(forwardedUrl("/pages/index"))
                .andExpect(model().attribute("questions", hasSize(2)))
                .andExpect(model().attribute("questions", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("title", is("titleTest1")),
                                hasProperty("author", is("testUserId")),
                                hasProperty("registerDate", is("2019-06-26 17:00")),
                                hasProperty("updateDate", is("2019-06-26 17:00"))
                        )
                )))
                .andExpect(model().attribute("questions", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("title", is("titleTest2")),
                                hasProperty("author", is("testUserId222")),
                                hasProperty("registerDate", is("2019-06-26 18:00")),
                                hasProperty("updateDate", is("2019-06-26 18:00"))
                        )
                )));
    }

    @Test
    public void createQuestion_질문생성후_redirection() throws Exception {

        //given
        QuestionReqDto questionReqDto = QuestionReqDto.builder()
                .content("content111")
                .title("titleTest1")
                .build();

        Mockito.doNothing().when(questionService).createQuestion(questionReqDto, 1L);

        //when
        mvc.perform(post("/questions"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void getQuestionDetails() throws Exception {

        Long id = 1L;


        //given
        Mockito.when(questionService.getQuestionDetail(id))
                .thenReturn(questionDetailResDto);

        mvc.perform(get("/questions/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/pages/show"))
                .andExpect(forwardedUrl("/pages/show"))
                .andExpect(model().attribute("question",
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("title", is("titleTest1")),
                                hasProperty("content", is("contentTest1")),
                                hasProperty("author", is("testUserId")),
                                hasProperty("registerDate", is("2019-06-26 17:00")),
                                hasProperty("updateDate", is("2019-06-26 17:00"))
                        )
                ));
    }

    @Test
    public void getEditFormOfQuestion() throws Exception {

        Long id = 1L;

        //given
        Mockito.when(questionService.getQuestionDetail(id))
                .thenReturn(questionDetailResDto);

        mvc.perform(get("/questions/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/pages/questionUpdateForm"))
                .andExpect(forwardedUrl("/pages/questionUpdateForm"))
                .andExpect(model().attribute("question",
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("title", is("titleTest1")),
                                hasProperty("content", is("contentTest1")),
                                hasProperty("author", is("testUserId")),
                                hasProperty("registerDate", is("2019-06-26 17:00")),
                                hasProperty("updateDate", is("2019-06-26 17:00"))
                        )
                ));
    }

    @Test
    public void updateQuestion_질문수정후_redirection() throws Exception {

        //given
        QuestionUpdateReqDto questionUpdateReqDto = QuestionUpdateReqDto.builder()
                .content("content111")
                .title("titleTest1")
                .build();

        Long id = 1L;
        Mockito.doNothing().when(questionService).updateQuestion(questionUpdateReqDto, id);

        //when
        mvc.perform(put("/questions/edit/{id}", id))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/questions/" + id))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void deleteQuestion_질문삭제후_redirection() throws Exception {
        Long id = 1L;
        mvc.perform(delete("/questions/delete/{id}", id))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andExpect(status().is3xxRedirection());
        Mockito.verify(questionService).deleteQuestion(id);
    }
}