package com.point72.invertservice.controller;

import com.point72.invertservice.model.InvertResponse;
import com.point72.invertservice.service.InvertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InvertController.class)
@AutoConfigureMockMvc(addFilters = false)
class InvertControllerTest {

    @SpringBootApplication(
            scanBasePackages = "com.point72.invertservice.controller",
            exclude = {
                    JpaRepositoriesAutoConfiguration.class,
                    HibernateJpaAutoConfiguration.class,
                    DataSourceAutoConfiguration.class
            }
    )
    static class TestConfig {
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvertService invertService;

    @Test
    void testInvertWords() throws Exception {
        String input = "abc def";
        InvertResponse mockResponse = InvertResponse.builder()
                .id(1L)
                .requestText(input)
                .responseText("cba fed")
                .createdAt(LocalDateTime.now())
                .build();

        when(invertService.processAndSave(input)).thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/invert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sentence\": \"abc def\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseText").value("cba fed"));
    }

    @Test
    void testSearchByWord() throws Exception {
        String word = "hello";
        InvertResponse mockEntry = InvertResponse.builder()
                .id(10L)
                .requestText("hello world")
                .responseText("olleh dlrow")
                .createdAt(LocalDateTime.now())
                .build();

        when(invertService.searchByWord(word)).thenReturn(List.of(mockEntry));

        mockMvc.perform(get("/api/v1/invert/search")
                        .param("word", "hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].requestText").value("hello world"))
                .andExpect(jsonPath("$[0].responseText").value("olleh dlrow"));
    }

    @Test
    void testHistory() throws Exception {
        InvertResponse mockEntry = InvertResponse.builder()
                .id(5L)
                .requestText("test")
                .responseText("tset")
                .createdAt(LocalDateTime.now())
                .build();

        when(invertService.findAll()).thenReturn(List.of(mockEntry));

        mockMvc.perform(get("/api/v1/invert/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].responseText").value("tset"));
    }

}