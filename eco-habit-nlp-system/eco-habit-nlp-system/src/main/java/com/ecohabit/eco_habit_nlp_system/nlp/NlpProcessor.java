package com.ecohabit.eco_habit_nlp_system.nlp;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class NlpProcessor {

    private static final Map<String, String> keywords = Map.of(
            "car", "transport",
            "bike", "transport",
            "bus", "transport",
            "plastic", "plastic",
            "bottle", "plastic",
            "electricity", "energy",
            "fan", "energy",
            "ac", "energy",
            "water", "water"
    );

    public String detectCategory(String text) {

        // ✅ MUST-HAVE safety check
        if (text == null || text.trim().isEmpty()) {
            return "general";
        }

        final String lowerText = text.toLowerCase();

        return keywords.entrySet().stream()
                .filter(e -> lowerText.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("general");
    }
}
