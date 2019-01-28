package com.github.edpilots.quest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"name"})
public class State {
    @JsonProperty("State")
    private String name;
}
