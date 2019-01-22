package com.github.edpilots.quest.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EdmcController {

    @PostMapping("/edmc")
    @ResponseBody
    public String edmc(@RequestHeader("egp-token") String token, @RequestBody String data) {
        log.debug("Token: {}", token);
        log.debug("Data: {}", data);
        return "{status: 'ok'}";
    }
}
