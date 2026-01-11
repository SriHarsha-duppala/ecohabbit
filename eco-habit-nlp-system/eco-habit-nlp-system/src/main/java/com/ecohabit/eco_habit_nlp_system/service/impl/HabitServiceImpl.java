package com.ecohabit.eco_habit_nlp_system.service.impl;

import com.ecohabit.eco_habit_nlp_system.model.Habit;
import com.ecohabit.eco_habit_nlp_system.nlp.NlpProcessor;
import com.ecohabit.eco_habit_nlp_system.repository.HabitRepository;
import com.ecohabit.eco_habit_nlp_system.service.HabitService;
import com.ecohabit.eco_habit_nlp_system.utils.BadgeEngine;
import com.ecohabit.eco_habit_nlp_system.utils.CarbonFootprintCalculator;
import com.ecohabit.eco_habit_nlp_system.utils.EcoScoreCalculator;
import com.ecohabit.eco_habit_nlp_system.utils.RecommendationEngine;
import org.springframework.stereotype.Service;

@Service
public class HabitServiceImpl implements HabitService {

    private final HabitRepository repo;
    private final NlpProcessor nlp;
    private final EcoScoreCalculator scoreCalc;
    private final RecommendationEngine recommender;
    private final CarbonFootprintCalculator carbonCalc;
    private final BadgeEngine badgeEngine;

    public HabitServiceImpl(HabitRepository repo,
                            NlpProcessor nlp,
                            EcoScoreCalculator scoreCalc,
                            RecommendationEngine recommender,
                            CarbonFootprintCalculator carbonCalc,
                            BadgeEngine badgeEngine) {

        this.repo = repo;
        this.nlp = nlp;
        this.scoreCalc = scoreCalc;
        this.recommender = recommender;
        this.carbonCalc = carbonCalc;
        this.badgeEngine = badgeEngine;
    }

    @Override
    public Habit analyzeHabit(String text, Long userId) {

        // ✅ FINAL SAFETY NET
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Habit text cannot be null or empty");
        }

        String category = nlp.detectCategory(text);
        int score = scoreCalc.calculate(category);
        String recommendation = recommender.suggest(category);
        double carbon = carbonCalc.calculate(category);
        String badge = badgeEngine.assignBadge(score);

        Habit habit = new Habit();
        habit.setUserId(userId);
        habit.setHabitText(text);
        habit.setDetectedCategory(category);
        habit.setEcoScore(score);
        habit.setCarbonFootprint(carbon);
        habit.setBadge(badge);
        habit.setRecommendation(recommendation);

        return repo.save(habit);
    }
}
