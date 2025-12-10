package com.point72.invertservice.controller;

import com.point72.invertservice.model.InvertRequest;
import com.point72.invertservice.model.InvertResponse;
import com.point72.invertservice.service.InvertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invert")
@RequiredArgsConstructor
@Tag(name = "Invert API", description = "APIs to invert words & manage request/response history")
public class InvertController {

    private final InvertService invertService;

    @PostMapping
    @Operation(
            summary = "Invert all words in a sentence",
            description = "Takes a sentence, inverts each word, stores request-response pair, and returns the result.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sentence inverted successfully",
                            content = @Content(schema = @Schema(implementation = InvertResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid Input",
                            content = @Content)
            }
    )
    public ResponseEntity<InvertResponse> invert(@Valid @RequestBody InvertRequest invertRequest) {
        InvertResponse response = invertService.processAndSave(invertRequest.getSentence());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search request/response history by word",
            description = "Returns all request-response records where the given word appears in either the request or response.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Matching records found",
                            content = @Content(schema = @Schema(implementation = InvertResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No matching records found",
                            content = @Content)
            }
    )
    public ResponseEntity<List<InvertResponse>> searchByWord(@RequestParam String word) {
        List<InvertResponse> responses = invertService.searchByWord(word);
        if(responses==null || responses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/history")
    @Operation(
            summary = "Fetch full processing history",
            description = "Returns all stored request-response entries.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of all records",
                            content = @Content(schema = @Schema(implementation = InvertResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No history found",
                            content = @Content)
            }
    )
    public ResponseEntity<List<InvertResponse>> history() {
        List<InvertResponse> responses = invertService.findAll();
        if(responses==null || responses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responses);
    }



}
