package com.ecohabit.eco_habit_nlp_system.utils;

import org.springframework.stereotype.Component;

@Component
public class EcoScoreCalculator {

    public int calculate(String category) {
        return switch (category) {
            case "transport" -> 50;
            case "plastic" -> 40;
            case "energy" -> 30;
            case "water" -> 20;
            default -> 10;
        };
    }
}
