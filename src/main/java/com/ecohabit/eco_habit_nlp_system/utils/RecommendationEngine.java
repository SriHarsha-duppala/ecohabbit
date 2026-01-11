package com.ecohabit.eco_habit_nlp_system.utils;

import org.springframework.stereotype.Component;

@Component
public class RecommendationEngine {

    public String suggest(String category) {
        return switch (category) {
            case "transport" -> "Use public transport or bicycle to reduce emissions.";
            case "plastic" -> "Switch to reusable bags and bottles.";
            case "energy" -> "Turn off unused electrical devices.";
            case "water" -> "Avoid water wastage and fix leaks.";
            default -> "Keep following eco-friendly habits!";
        };
    }
}
