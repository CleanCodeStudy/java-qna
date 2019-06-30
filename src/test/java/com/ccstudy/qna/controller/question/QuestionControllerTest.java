package com.ccstudy.qna.controller.question;

import com.ccstudy.qna.config.MvcConfig;
import com.ccstudy.qna.domain.question.QuestionRepository;
import com.ccstudy.qna.dto.question.QuestionListResponseDto;
import com.ccstudy.qna.service.question.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionController.class)
@ActiveProfiles("test")
@ComponentScan({"com.ccstudy.qna.interceptor","com.ccstudy.qna.resolver"})
public class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean(name = "questionServiceMock")
    private QuestionService questionService;

    @MockBean(name = "questionRepositoryMock")
    private QuestionRepository questionRepository;

    /*
     @GetMapping("/")
    public String index(@Auth LoginAccount loginAccount, Model model) {
        log.info(loginAccount.toString());
        model.addAttribute("questions", questionService.showQuestions());
        return "index";
    }
     */

    @Before
    public void setUp(){

    }

    @Test
    public void index() throws Exception {
        //given

        given(questionService.showQuestions()).willReturn(
                questionRepository.findAll().stream()
                        .map(QuestionListResponseDto::new)
                        .collect(Collectors.toList()));
        //when
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("questions",hasSize(2)))
                .andExpect(model().attribute("questions",hasItem(questionService.showQuestions())))
                .andDo(print());

        //thengi
    }

    @Test
    public void writeForm() {
        //given
        //when
        //then
    }

    @Test
    public void saveQuestion() {
        //given
        //when
        //then
    }

    @Test
    public void showQuestionDetail() {
        //given
        //when
        //then
    }

    @Test
    public void questionUpdateForm() {
        //given
        //when
        //then
    }

    @Test
    public void update() {
        //given
        //when
        //then
    }

    @Test
    public void delete() {
        //given
        //when
        //then
    }
}