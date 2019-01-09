package com.github.edpilots.quest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
// @EnableMongoRepositories
public class QuestServerApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(QuestServerApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/pilot").setViewName("/pilot/index");
        registry.addViewController("/").setViewName("/index");
    }

    
    
}
