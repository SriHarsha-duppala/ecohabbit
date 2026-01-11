package com.ecohabit.eco_habit_nlp_system.controller;

import com.ecohabit.eco_habit_nlp_system.dto.MonthlyAnalyticsDto;
import com.ecohabit.eco_habit_nlp_system.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "http://localhost:3000")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/monthly")
    public MonthlyAnalyticsDto getMonthlyAnalytics(
            @RequestParam Long userId,
            @RequestParam int year,
            @RequestParam int month) {

        return analyticsService.getMonthlyAnalytics(userId, year, month);
    }

    @GetMapping("/daily-eco-score")
    public List<Map<String, Object>> getDailyEcoScoreTrend(
            @RequestParam Long userId,
            @RequestParam int year,
            @RequestParam int month) {

        return analyticsService.getDailyEcoScoreTrend(userId, year, month);
    }
}
