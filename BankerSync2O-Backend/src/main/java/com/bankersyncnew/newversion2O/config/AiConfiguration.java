package com.bankersyncnew.newversion2O.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AiConfiguration {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Bean
    public RestClient aiConfig(){
        return RestClient.
                builder().
                defaultHeader("Authorization", "Bearer " + openaiApiKey).
                build();
    }

}
