package com.ecohabit.eco_habit_nlp_system.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CarbonFootprintCalculator {

    private static final Map<String, Double> carbonMap = Map.of(
            "transport", 2.5,   // kg CO₂
            "plastic", 1.8,
            "energy", 3.2,
            "water", 1.0,
            "general", 0.5
    );

    public double calculate(String category) {
        return carbonMap.getOrDefault(category, 0.5);
    }
}
