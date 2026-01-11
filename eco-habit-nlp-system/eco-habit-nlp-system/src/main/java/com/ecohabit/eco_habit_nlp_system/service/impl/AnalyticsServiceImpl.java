package com.ecohabit.eco_habit_nlp_system.service.impl;

import com.ecohabit.eco_habit_nlp_system.dto.MonthlyAnalyticsDto;
import com.ecohabit.eco_habit_nlp_system.model.Habit;
import com.ecohabit.eco_habit_nlp_system.repository.HabitRepository;
import com.ecohabit.eco_habit_nlp_system.service.AnalyticsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final HabitRepository habitRepository;

    public AnalyticsServiceImpl(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public MonthlyAnalyticsDto getMonthlyAnalytics(Long userId, int year, int month) {

        // 1️⃣ Date range for the month
        LocalDateTime start = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime end = start.plusMonths(1).minusSeconds(1);

        // 2️⃣ Repository call (returns Object[])
        List<Object[]> result =
                habitRepository.getMonthlyStats(userId, start, end);

        // SAFE: handle empty result
        Object[] row = result.isEmpty()
                ? new Object[]{0L, 0.0, 0.0}
                : result.get(0);

        // Extract values safely
        Long totalHabits = ((Number) row[0]).longValue();
        Double avgEcoScore = row[1] != null ? ((Number) row[1]).doubleValue() : 0.0;
        Double totalCarbon = row[2] != null ? ((Number) row[2]).doubleValue() : 0.0;

        return new MonthlyAnalyticsDto(
                totalHabits,
                avgEcoScore,
                totalCarbon,
                totalHabits > 0 ? "🌱 Beginner" : "No Badge"
        );
    }


    @Override
    public List<Map<String, Object>> getDailyEcoScoreTrend(Long userId, int year, int month) {

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);

        List<Object[]> rawData =
                habitRepository.getDailyEcoScoreTrend(userId, start, end);

        Map<Integer, Double> ecoScoreByDay = new HashMap<>();
        for (Object[] row : rawData) {
            Integer day = ((Number) row[0]).intValue();
            Double score = ((Number) row[1]).doubleValue();
            ecoScoreByDay.put(day, score);
        }

        List<Map<String, Object>> result = new ArrayList<>();
        int daysInMonth = startDate.lengthOfMonth();

        for (int day = 1; day <= daysInMonth; day++) {
            Map<String, Object> map = new HashMap<>();
            map.put("day", day);
            map.put("ecoScore", ecoScoreByDay.getOrDefault(day, 0.0));
            result.add(map);
        }

        return result;
    }
}
