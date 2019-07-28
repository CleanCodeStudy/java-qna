package com.ccstudy.qna.controller;

import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.domain.Question;
import com.ccstudy.qna.domain.repository.AccountRepository;
import com.ccstudy.qna.domain.repository.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @Transactional
    public void createQuestion_정상저장() throws Exception {
        //given
        String title = "sampleTitle";
        String content = "sampleContent";

        //when
        this.mockMvc.perform(post("/questions")
                .param("title", title)
                .param("content", content)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();

        //then
        Long idx = (long) questionRepository.findAll().get(0).getId();

        Question question = questionRepository.findById(idx)
                .orElseThrow(RuntimeException::new);

        assertThat(question.getTitle()).isEqualTo(title);
        assertThat(question.getContent()).isEqualTo(content);
    }

    @Test
    @Transactional
    public void updateQuestion_정상수정() throws Exception {
        //given
        String title = "changeTitle";
        String content = "changeContent";

        Account account = accountRepository.findAll().get(0);
        Question saveQuestion = Question.createBuilder()
                .content("testContent")
                .title("testTitle")
                .author(account)
                .build();
        Question question = questionRepository.save(saveQuestion);

        String originalTitle = question.getTitle();
        String originalContent = question.getContent();

        //when
        this.mockMvc.perform(put("/questions/{id}", question.getId())
                .param("title", title)
                .param("content", content)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();

        Question changedQuestion = questionRepository.findById(question.getId())
                .orElseThrow(RuntimeException::new);

        //then
        assertThat(changedQuestion.getTitle()).isEqualTo(title);
        assertThat(changedQuestion.getContent()).isEqualTo(content);

        assertThat(changedQuestion.getContent()).isNotEqualTo(originalContent);
        assertThat(changedQuestion.getTitle()).isNotEqualTo(originalTitle);
    }

    @Test
    @Transactional
    public void deleteQuestion() throws Exception {
        //given
        Account account = accountRepository.findAll().get(0);
        Question saveQuestion = Question.createBuilder()
                .content("testContent")
                .title("testTitle")
                .author(account)
                .build();
        Question question = questionRepository.save(saveQuestion);

        //when
        this.mockMvc.perform(delete("/questions/{id}", question.getId())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();

        Question deleteQuestion = questionRepository.findById(question.getId())
                .orElseThrow(RuntimeException::new);

        assertThat(deleteQuestion.isDeleted()).isTrue();
    }
}