package com.github.edpilots.quest.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PilotToken {
    @Id
    private String id;
    private String pilot;
    private String token;
}
