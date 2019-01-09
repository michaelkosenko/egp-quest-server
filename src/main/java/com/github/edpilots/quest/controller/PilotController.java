package com.github.edpilots.quest.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.edpilots.quest.model.Quest;
import com.github.edpilots.quest.model.QuestInstance;
import com.github.edpilots.quest.model.PilotToken;
import com.github.edpilots.quest.repository.QuestInstanceRepository;
import com.github.edpilots.quest.repository.PilotTokenRepository;

@Controller
@RequestMapping("/pilot")
public class PilotController {
    
    //@Autowired
    //private QuestInstanceRepository questInstanceRepository;
    @Autowired
    private PilotTokenRepository tokenRepository;
    
    @GetMapping("/api")
    public String apiToken(Model model, OAuth2AuthenticationToken authentication) {
        //get token from database
        String pilot = authentication.getName();
        PilotToken token = tokenRepository.findByPilot(pilot);
        
        if (token == null) {
            token = PilotToken.builder().pilot(pilot).token(UUID.randomUUID().toString()).build();
            token = tokenRepository.save(token);
        }
        
        model.addAttribute("token", token.getToken());
        return "/pilot/api";
    }
    
    @PostMapping("/api")
    public String generateToken(Model model, OAuth2AuthenticationToken authentication) {
        String pilot = authentication.getName();
        PilotToken token = tokenRepository.findByPilot(pilot);
        token.setToken(UUID.randomUUID().toString());
        tokenRepository.save(token);
        model.addAttribute("token", token.getToken());
        return "/pilot/api";
    }
    
}
