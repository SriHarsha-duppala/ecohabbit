package com.ecohabit.eco_habit_nlp_system.dto;

public class MonthlyAnalyticsDto {

    private Long totalHabits;
    private Double avgEcoScore;
    private Double totalCarbon;
    private String topBadge;

    public MonthlyAnalyticsDto(Long totalHabits,
                               Double avgEcoScore,
                               Double totalCarbon,
                               String topBadge) {
        this.totalHabits = totalHabits;
        this.avgEcoScore = avgEcoScore;
        this.totalCarbon = totalCarbon;
        this.topBadge = topBadge;
    }

    public Long getTotalHabits() {
        return totalHabits;
    }

    public Double getAvgEcoScore() {
        return avgEcoScore;
    }

    public Double getTotalCarbon() {
        return totalCarbon;
    }

    public String getTopBadge() {
        return topBadge;
    }
}
