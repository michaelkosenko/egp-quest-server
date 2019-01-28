package com.github.edpilots.quest.edmc.handler;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.edpilots.quest.model.StarSystem;
import com.github.edpilots.quest.model.StarSystemComparator;
import com.github.edpilots.quest.repository.StarSystemRepository;

import lombok.extern.slf4j.Slf4j;

@Event("Location")
@Slf4j
public class LocationHandler implements Handler {
    
    private final StarSystemRepository repository;
    
    public LocationHandler(StarSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(JSONObject json) {
        try {
            try {
                String event = json.getString("event");
                if (!"Location".equals(event) && !"FSDJump".equals(event)) {
                    return;
                }
            } catch(JSONException ex) {
                log.debug(ex.toString(), ex);
                return;
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            
            StarSystem value = mapper.readValue(json.toString(), StarSystem.class);
            Example<StarSystem> example = Example.of(value);
            /*
            List<StarSystem> all = repository.findAll(example, Sort.by(Direction.DESC, "lastModified"));
            if (all.size() > 0) {
                StarSystem s = all.get(0);
                if (Objects.compare(s, value, new StarSystemComparator()) == 0) {
                    Date lastUpdated = value.getLastUpdated();
                    value = s;
                    value.setLastUpdated(lastUpdated);
                }
            }
            */
            
            StarSystem system = repository.findOneByName(value.getName());
            if (system != null) {
                value.setId(system.getId());
            }
            
            repository.save(value);
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
