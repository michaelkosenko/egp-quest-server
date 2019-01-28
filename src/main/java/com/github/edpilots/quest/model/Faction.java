package com.github.edpilots.quest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
public class Faction {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("FactionState")
    private String state;
    @JsonProperty("Government")
    private String goverment;
    @JsonProperty("Influence")
    private Float influence;
    @JsonProperty("Allegiance")
    private String allegiance;
    @JsonProperty("Happiness")
    private String happiness;
    @JsonProperty("ActiveStates")
    private List<State> activeStates;
}
