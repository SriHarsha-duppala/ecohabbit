package com.ecohabit.eco_habit_nlp_system.utils;

import org.springframework.stereotype.Component;

@Component
public class BadgeEngine {

    public String assignBadge(int ecoScore) {

        if (ecoScore >= 90) {
            return "🌟 Eco Champion";
        } else if (ecoScore >= 70) {
            return "🥇 Green Hero";
        } else if (ecoScore >= 50) {
            return "🥈 Eco Starter";
        } else {
            return "🌱 Beginner";
        }
    }
}
