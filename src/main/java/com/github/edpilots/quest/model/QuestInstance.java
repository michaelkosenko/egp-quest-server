package com.github.edpilots.quest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestInstance {
    //@Id
    private String id;
    
    private String pilotName;
}
