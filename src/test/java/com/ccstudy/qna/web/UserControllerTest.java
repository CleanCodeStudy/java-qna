package com.ccstudy.qna.web;

import com.ccstudy.qna.domain.entity.User;
import com.ccstudy.qna.domain.repository.UserRepository;
import com.ccstudy.qna.util.LocalDateTimeConverter;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void 유저_생성_테스트() throws Exception {
        //given
        //when
        this.mockMvc.perform(post("/users")
                .param("email", "chldbtjd7530@naver.com")
                .param("password", "qweqwe")
                .param("confirmPassword", "qweqwe")
                .param("firstName", "chl")
                .param("lastName", "ys")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();

        //then
        User user = repository.findAll().get(0);

        assertThat(user.getEmail()).isEqualTo("chldbtjd7530@naver.com");
        assertThat(user.getPassword()).isEqualTo("qweqwe");
        assertThat(user.getFirstName()).isEqualTo("chl");
        assertThat(user.getLastName()).isEqualTo("ys");

        assertThat(LocalDateTimeConverter.convertLocalDate(user.getCreatedDateTime()))
                .isEqualTo(LocalDateTimeConverter.convertLocalDate(LocalDateTime.now()));


        assertThat(LocalDateTimeConverter.convertLocalDate(user.getModifiedDateTime()))
                .isEqualTo(LocalDateTimeConverter.convertLocalDate(LocalDateTime.now()));

    }


    @Test
    public void 유저_수정_테스트() throws Exception {
        //given
        repository.save(User.createBuilder().email("chldbtjd2272@naver.com").password("qweqwe").firstName("q").lastName("sj").build());

        //when
        this.mockMvc.perform(put("/users")
                .param("id", "1")
                .param("email", "chldbtjd7530@naver.com")
                .param("password", "qweqwe")
                .param("confirmPassword", "qweqwe")
                .param("firstName", "chl")
                .param("lastName", "ys")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();

        //then
        User user = repository.findAll().get(0);

        assertThat(user.getEmail()).isEqualTo("chldbtjd7530@naver.com");
        assertThat(user.getPassword()).isEqualTo("qweqwe");
        assertThat(user.getFirstName()).isEqualTo("chl");
        assertThat(user.getLastName()).isEqualTo("ys");

        assertThat(LocalDateTimeConverter.convertLocalDate(user.getCreatedDateTime()))
                .isEqualTo(LocalDateTimeConverter.convertLocalDate(LocalDateTime.now()));


        assertThat(LocalDateTimeConverter.convertLocalDate(user.getModifiedDateTime()))
                .isEqualTo(LocalDateTimeConverter.convertLocalDate(LocalDateTime.now()));

    }
}
