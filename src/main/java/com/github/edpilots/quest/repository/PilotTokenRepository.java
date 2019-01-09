package com.github.edpilots.quest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.github.edpilots.quest.model.PilotToken;

public interface PilotTokenRepository extends MongoRepository<PilotToken, String> {

    PilotToken findByPilot(String pilot);
    
    PilotToken findByToken(String token);

}
