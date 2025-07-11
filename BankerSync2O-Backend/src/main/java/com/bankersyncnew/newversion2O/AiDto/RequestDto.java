package com.bankersyncnew.newversion2O.AiDto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
public class RequestDto {
    private String model;
    private List<Message> messages;

    public RequestDto(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user",prompt));
    }

}
