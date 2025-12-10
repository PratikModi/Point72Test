package com.point72.invertservice.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvertRequest {
    @NotBlank(message = "Sentence cannot be empty!!")
    private String sentence;
}
