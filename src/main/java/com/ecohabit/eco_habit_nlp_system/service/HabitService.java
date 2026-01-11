package com.ecohabit.eco_habit_nlp_system.service;

import com.ecohabit.eco_habit_nlp_system.model.Habit;

public interface HabitService {
    Habit analyzeHabit(String text, Long userId);
}
