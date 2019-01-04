package com.github.edpilots.quest.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.edpilots.quest.model.Quest;
import com.github.edpilots.quest.model.QuestInstance;
import com.github.edpilots.quest.repository.QuestInstanceRepository;

@Controller
@RequestMapping("/pilot")
public class PilotController {
    
    //@Autowired
    //private QuestInstanceRepository questInstanceRepository;
    
    @GetMapping("/quests")
    public String getQuests(Model model, OAuth2AuthenticationToken authentication) {
        
        //List<QuestInstance> list = questInstanceRepository.findAllByPilotName(principal.getName());
        
        //model.addAttribute("quests", list);
        
        //System.out.println(principal.getPrincipal().getAttributes().get("email"));
        
        return "/pilot/quests";
    }
    
    
}
