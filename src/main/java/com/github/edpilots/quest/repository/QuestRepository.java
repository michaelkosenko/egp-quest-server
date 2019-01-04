package com.github.edpilots.quest.repository;

import java.util.List;

import com.github.edpilots.quest.model.Quest;

public interface QuestRepository /*extends MongoRepository<Quest, String> */{
    
    List<Quest> findAll();

}
