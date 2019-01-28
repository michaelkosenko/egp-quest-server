package com.github.edpilots.quest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.edpilots.quest.edmc.handler.Handler;
import com.github.edpilots.quest.edmc.handler.LocationHandler;
import com.github.edpilots.quest.repository.StarSystemRepository;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EdmcController {
    
    private List<Handler> handlers;
    
    @Autowired
    private StarSystemRepository repository;
    
    @PostConstruct
    public void init() {
        handlers = new ArrayList<>();
        handlers.add(new LocationHandler(repository));
    }

    @PostMapping("/edmc")
    @ResponseBody
    public Response edmc(@RequestHeader("egp-token") String token, @RequestBody String data) {
        log.debug("Token: {}", token);
        log.debug("Data: {}", data);
        
        try {
            JSONObject json = new JSONObject(data);
            handlers.stream().forEach(handler -> handler.handle(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Response.builder().code(200).message("ok").build();
    }
    
    @Builder
    @Data
    public static class Response {
        private int code;
        private String message;
    }
}
