package com.ecohabit.eco_habit_nlp_system.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://ecohabit-nlp.netlify.app/")
public class VoiceController {

    @PostMapping("/api/voice/text")
    public String receiveVoiceText(@RequestBody String text) {
        return "Received voice text: " + text;
    }
}

