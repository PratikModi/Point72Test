package com.point72.invertservice.service;

import com.point72.invertservice.model.RequestResponse;
import com.point72.invertservice.respository.RequestResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvertService {

    private final RequestResponseRepository repository;

    public String invertWords(String sentence){
        if(sentence==null || sentence.isBlank()) return sentence;
        String[] words = sentence.split("\\s+");

        StringBuilder sb = new StringBuilder();
        for(String word : words){
            sb.append(new StringBuilder(word).reverse().append(" "));
        }
        return sb.toString().trim();
    }

    public RequestResponse processAndSave(String request){
        String response = invertWords(request);
        RequestResponse rr = RequestResponse.builder()
                .requestText(request)
                .responseText(response)
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(rr);
    }

    public List<RequestResponse> searchByWord(String word){
        return repository.searchByWord(word);
    }

    public List<RequestResponse> findAll(){
        return repository.findAll();
    }

}
