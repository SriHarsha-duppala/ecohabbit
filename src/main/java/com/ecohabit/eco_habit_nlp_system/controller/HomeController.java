package com.ecohabit.eco_habit_nlp_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "✅ Eco Habit NLP Backend is running successfully!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
