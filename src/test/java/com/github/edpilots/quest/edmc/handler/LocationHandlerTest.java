package com.github.edpilots.quest.edmc.handler;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.edpilots.quest.model.StarSystem;

import lombok.extern.slf4j.Slf4j;

//@DataMongoTest
//@ExtendWith(SpringExtension.class)
@Slf4j
public class LocationHandlerTest {

    @Test
    @Ignore
    public void testHandle() throws Exception {
        String string = IOUtils.resourceToString("location.json", Charset.forName("UTF-8"), getClass().getClassLoader());
        
        JSONTokener tokener = new JSONTokener(string);
        JSONObject json = new JSONObject(tokener);
        
        LocationHandler locationHandler = new LocationHandler(null);
        locationHandler.handle(json);
        
        
        
        
    }
    
    @Test
    public void testHandle2() throws Exception {
        String string = IOUtils.resourceToString("location.json", Charset.forName("UTF-8"), getClass().getClassLoader());
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        StarSystem value = mapper.readValue(string, StarSystem.class);
        log.debug(value.toString());
    }

}
