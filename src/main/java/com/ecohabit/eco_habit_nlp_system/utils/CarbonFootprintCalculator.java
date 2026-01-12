package com.ecohabit.eco_habit_nlp_system.utils;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class CarbonFootprintCalculator {

    private static final Map<String, Double> carbonMap = Map.of(

            // ❌ High pollution habits
            "transport", 3.0,   // car, bus
            "energy", 2.5,      // electricity, AC
            "plastic", 2.0,     // plastic usage

            // ✅ Low pollution habits
            "water", 1.0,       // water saving
            "general", 0.8      // neutral
    );

    public double calculate(String category) {
        return carbonMap.getOrDefault(category, 0.8);
    }
}
