package com.point72.invertservice.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InvertRequest {
    @NotBlank(message = "Sentence cannot be empty!!")
    private String sentence;
}
