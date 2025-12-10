package com.point72.invertservice.respository;

import com.point72.invertservice.model.RequestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestResponseRepository extends JpaRepository<RequestResponse,Long> {

    @Query("SELECT rr FROM RequestResponse rr " +
            "WHERE LOWER(rr.requestText) LIKE LOWER(CONCAT('%', :word, '%')) " +
            "   OR LOWER(rr.responseText) LIKE LOWER(CONCAT('%', :word, '%'))")
    List<RequestResponse> searchByWord(String word);

    List<RequestResponse> findByRequestTextContainingIgnoreCaseOrResponseTextContainingIgnoreCase(String requestText, String responseText);
}
