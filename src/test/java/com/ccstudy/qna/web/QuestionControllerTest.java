package com.ccstudy.qna.web;

import com.ccstudy.qna.domain.entity.Question;
import com.ccstudy.qna.domain.repository.QuestionRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuestionRepository repository;

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void 질문_생성_성공() throws Exception {

        //given
        //then
        //when
        this.mockMvc.perform(post("/questions")
                .param("title", "aaa")
                .param("contents", "aaaa")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
        Question question = repository.findAll().get(0);

        assertThat(question.getTitle()).isEqualTo("aaa");
        assertThat(question.getContents()).isEqualTo("aaaa");
    }

    @Test
    public void 질문_제목이_없을시_실패() throws Exception {

        //given
        //then
        //when
        this.mockMvc.perform(post("/questions")
                .param("title", "aaa")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    public void 질문_내용이_없을시_실패() throws Exception {

        //given
        //then
        //when
        this.mockMvc.perform(post("/questions")
                .param("contents", "aaaa")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

    }
}