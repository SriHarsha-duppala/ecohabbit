package com.ecohabit.eco_habit_nlp_system.nlp;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class NlpProcessor {

    private static final Map<String, String> keywords = Map.ofEntries(

            // 🚗 TRANSPORT (high pollution)
            Map.entry("car", "transport"),
            Map.entry("bike", "transport"),
            Map.entry("bus", "transport"),
            Map.entry("train", "transport"),
            Map.entry("auto", "transport"),
            Map.entry("flight", "transport"),
            Map.entry("petrol", "transport"),
            Map.entry("diesel", "transport"),
            Map.entry("uber", "transport"),
            Map.entry("ola", "transport"),

            // 🧴 PLASTIC
            Map.entry("plastic", "plastic"),
            Map.entry("bottle", "plastic"),
            Map.entry("bag", "plastic"),
            Map.entry("cover", "plastic"),
            Map.entry("cup", "plastic"),
            Map.entry("straw", "plastic"),
            Map.entry("wrapper", "plastic"),
            Map.entry("packet", "plastic"),

            // ⚡ ENERGY
            Map.entry("electricity", "energy"),
            Map.entry("current", "energy"),
            Map.entry("fan", "energy"),
            Map.entry("ac", "energy"),
            Map.entry("air conditioner", "energy"),
            Map.entry("heater", "energy"),
            Map.entry("light", "energy"),
            Map.entry("bulb", "energy"),
            Map.entry("tv", "energy"),
            Map.entry("fridge", "energy"),

            // 💧 WATER
            Map.entry("water", "water"),
            Map.entry("tap", "water"),
            Map.entry("shower", "water"),
            Map.entry("bath", "water"),
            Map.entry("washing", "water"),
            Map.entry("laundry", "water"),
            Map.entry("tank", "water"),
            Map.entry("pipe", "water")
    );

    public String detectCategory(String text) {

        // ✅ SAFETY CHECK (unchanged)
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
