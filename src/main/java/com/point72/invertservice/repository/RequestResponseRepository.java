package com.point72.invertservice.repository;

import com.point72.invertservice.model.InvertResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestResponseRepository extends JpaRepository<InvertResponse,Long> {

    List<InvertResponse> findByRequestTextContainingIgnoreCaseOrResponseTextContainingIgnoreCase(String requestText, String responseText);
}
