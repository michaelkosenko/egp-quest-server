package com.github.edpilots.quest;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.ToString;

@ConfigurationProperties("discord")
@Data
public class DiscordProperties {
    private Bot bot;
    
    @Data
    public static class Bot {
        private String token;
    }
}
