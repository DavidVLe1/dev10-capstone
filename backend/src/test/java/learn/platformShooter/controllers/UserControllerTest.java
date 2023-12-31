package learn.platformShooter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.platformShooter.data.UserRepository;
import learn.platformShooter.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @MockBean
    UserRepository userRepository;
    @Autowired
    MockMvc mvc;
//    @Test
//    void shouldFindAll() {
//    }
//
//    @Test
//    void shouldFindById() {
//    }
//
//    @Test
//    void shouldFindByEmail() {
//    }

    @Test
    void addShouldReturn201() throws Exception{
        User user= new User (0,"Jackie","Obama", "Jbama", "Jbama@gmail.com", "123password","blue","Female");
        User expected = new User (5,"Jackie","Obama", "Jbama", "Jbama@gmail.com", "123password","blue","Female");
        when(userRepository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();
        String userJson = jsonMapper.writeValueAsString (user);
        String expectedJson =jsonMapper.writeValueAsString (expected);

        var request = post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {
        var request = post("/api/user")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

//    @Test
//    void shouldUpdate() {
//    }
//
//    @Test
//    void shouldDeleteById() {
//    }
}