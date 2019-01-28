package com.github.edpilots.quest.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties({ "SystemEconomy_Localised"})
@Data
@ToString
public class StarSystem {
    @Id
    private String id;
    @JsonProperty("StarSystem")
    private String name;
    @JsonProperty("Population")
    private Long population;
    @JsonProperty("timestamp")
    private Date lastUpdated;
    @JsonProperty("Factions")
    private List<Faction> factions;
    @LastModifiedDate
    private Date lastModified;
    
}
