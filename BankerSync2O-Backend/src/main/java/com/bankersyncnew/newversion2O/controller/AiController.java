package com.bankersyncnew.newversion2O.controller;

import com.bankersyncnew.newversion2O.AiDto.RequestDto;
import com.bankersyncnew.newversion2O.AiDto.ResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/bot")
public class AiController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    private final RestClient restClient;
    public AiController(RestClient restClient) {
        this.restClient = restClient;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody RequestDto request) {

        request.setModel(model);

        ResponseDto chatGptResponse = restClient.post()
                .uri(apiURL)
                .body(request)
                .retrieve()
                .body(ResponseDto.class);

        return chatGptResponse.getChoices().get(0).getMessage().getContent();

    }
}
