package com.ecohabit.eco_habit_nlp_system.utils;

import org.springframework.stereotype.Component;

@Component
public class EcoScoreCalculator {

    public int calculate(String category) {
        return switch (category) {

            // ❌ High pollution → LOW eco score
            case "transport" -> 20;   // car, bus
            case "plastic"   -> 30;   // plastic usage
            case "energy"    -> 40;   // electricity wastage
            case "water"     -> 50;   // water usage

            // ✅ Neutral / unknown
            default -> 60;
        };
    }
}
