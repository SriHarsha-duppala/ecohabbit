package com.ecohabit.eco_habit_nlp_system.dto;

import java.time.LocalDate;

public class DailyEcoScoreDto {

    private LocalDate date;
    private Double avgEcoScore;

    public DailyEcoScoreDto(LocalDate date, Double avgEcoScore) {
        this.date = date;
        this.avgEcoScore = avgEcoScore;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAvgEcoScore() {
        return avgEcoScore;
    }
}
