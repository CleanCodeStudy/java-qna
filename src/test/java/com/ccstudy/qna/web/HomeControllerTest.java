package com.ccstudy.qna.web;

import com.ccstudy.qna.domain.entity.User;
import com.ccstudy.qna.domain.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void 로그인_테스트() throws Exception {
        //given
        saveUser();
        //when
        //then
        this.mockMvc.perform(post("/login")
                .param("email", "chldbtjd7530@naver.com")
                .param("password", "qweqwe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    public void 로그인_실패() throws Exception {
        //given
        saveUser();
        //when
        //then
        this.mockMvc.perform(post("/login")
                .param("email", "cys@naver.com")
                .param("password", "qweqwe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    private void saveUser() {
        repository.save(createUser());
    }

    private User createUser() {
        return User.createBuilder()
                .firstName("c")
                .lastName("ys")
                .email("chldbtjd7530@naver.com")
                .password("qweqwe")
                .build();
    }

}
