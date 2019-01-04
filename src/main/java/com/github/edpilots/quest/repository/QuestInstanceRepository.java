package com.github.edpilots.quest.repository;

import java.util.List;

import com.github.edpilots.quest.model.QuestInstance;

public interface QuestInstanceRepository /*extends MongoRepository<QuestInstance, String>*/ {
    
    List<QuestInstance> findAllByPilotName(String pilotName);
    
}
