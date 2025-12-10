package com.point72.invertservice.controller;

import com.point72.invertservice.model.InvertRequest;
import com.point72.invertservice.model.RequestResponse;
import com.point72.invertservice.respository.RequestResponseRepository;
import com.point72.invertservice.service.InvertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invert")
@RequiredArgsConstructor
public class InvertController {

    private final InvertService invertService;
    private final RequestResponseRepository requestResponseRepository;

    @PostMapping
    public RequestResponse invert(@Valid @RequestBody InvertRequest invertRequest) {
        return this.invertService.processAndSave(invertRequest.getSentence());
    }

    @GetMapping("/search")
    public List<RequestResponse> searchByWord(@RequestParam String word) {
        return requestResponseRepository.findByRequestTextContainingIgnoreCaseOrResponseTextContainingIgnoreCase(word, word);
    }

    @GetMapping("/history")
    public List<RequestResponse> history() {
        return requestResponseRepository.findAll();
    }



}
