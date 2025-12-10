package com.point72.invertservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "request_response")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvertResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String requestText;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String responseText;

    private LocalDateTime createdAt;
}
