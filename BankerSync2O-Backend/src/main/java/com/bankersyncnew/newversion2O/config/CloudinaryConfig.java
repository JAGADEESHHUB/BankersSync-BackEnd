package com.bankersyncnew.newversion2O.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dhofol0xp");
        config.put("api_key", "953678758877533");
        config.put("api_secret", "nXPicAWgXdRFwwdsSnzflwNTmgw");
        return new Cloudinary(config);
    }
}

