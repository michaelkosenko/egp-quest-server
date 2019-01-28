package com.github.edpilots.quest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.edpilots.quest.model.StarSystem;
import com.github.edpilots.quest.repository.StarSystemRepository;

@Controller
@RequestMapping("/system")
public class StarSystemController {
    
    @Autowired
    private StarSystemRepository repository;
    
    
    @RequestMapping("/all")
    public String all(Model model) {
        List<StarSystem> all = repository.findAll();
        model.addAttribute("systems", all);
        return "system/all";
    }
}
