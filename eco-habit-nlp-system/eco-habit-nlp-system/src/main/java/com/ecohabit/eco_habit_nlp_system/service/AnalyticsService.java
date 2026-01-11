package com.ecohabit.eco_habit_nlp_system.service;

import com.ecohabit.eco_habit_nlp_system.dto.MonthlyAnalyticsDto;

import java.util.List;
import java.util.Map;

public interface AnalyticsService {

    MonthlyAnalyticsDto getMonthlyAnalytics(
            Long userId,
            int year,
            int month
    );

    List<Map<String, Object>> getDailyEcoScoreTrend(
            Long userId,
            int year,
            int month
    );
}
