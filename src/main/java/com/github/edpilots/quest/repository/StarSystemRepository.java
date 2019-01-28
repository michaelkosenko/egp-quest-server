package com.github.edpilots.quest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.github.edpilots.quest.model.StarSystem;

public interface StarSystemRepository extends MongoRepository<StarSystem, String> {
    StarSystem findOneByName(String name);
}
