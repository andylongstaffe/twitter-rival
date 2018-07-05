package com.hollywood.twitterrival;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder().setDateFormat("EEE MMM dd HH:mm:ss Z yyyy").create();
    }
}
