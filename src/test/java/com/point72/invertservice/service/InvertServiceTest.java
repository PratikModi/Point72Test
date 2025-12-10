package com.point72.invertservice.service;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.point72.invertservice.model.InvertRequest;
import com.point72.invertservice.model.InvertResponse;
import com.point72.invertservice.repository.RequestResponseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InvertServiceTest {

    @Mock
    private RequestResponseRepository repository;

    @InjectMocks
    private InvertService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInvertSentence() {
        InvertRequest request = new InvertRequest("abc def");

        when(repository.save(any(InvertResponse.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        InvertResponse response = service.processAndSave(request.getSentence());

        // Then
        assertThat(response.getResponseText()).isEqualTo("cba fed");
        verify(repository, times(1)).save(any(InvertResponse.class));
    }

    @Test
    void testGetAllRecords() {
        InvertResponse e1 = new InvertResponse(1L, "abc", "cba", LocalDateTime.now());
        InvertResponse e2 = new InvertResponse(2L, "hello", "olleh",LocalDateTime.now());

        when(repository.findAll()).thenReturn(Arrays.asList(e1, e2));

        // When
        List<InvertResponse> result = service.findAll();

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getRequestText()).isEqualTo("abc");
        verify(repository, times(1)).findAll();
    }

    // ----------------------------
    // Test: findByWord()
    // ----------------------------
    @Test
    void testFindByWord() {
        // Given
        String word = "hello";
        InvertResponse record = new InvertResponse(1L, "hello world", "olleh dlrow",LocalDateTime.now());

        when(repository.findByRequestTextContainingIgnoreCaseOrResponseTextContainingIgnoreCase(word,word))
                .thenReturn(Arrays.asList(record));

        // When
        List<InvertResponse> result = service.searchByWord(word);

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getRequestText()).contains("hello");

        verify(repository, times(1))
                .findByRequestTextContainingIgnoreCaseOrResponseTextContainingIgnoreCase(word,word);
    }
}