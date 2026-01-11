package com.ecohabit.eco_habit_nlp_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(length = 1000)
    private String habitText;

    private String detectedCategory;

    private int ecoScore;

    private String recommendation;

    // ✅ Carbon Footprint
    private Double carbonFootprint;

    // ✅ Badge
    private String badge;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ✅ GUARANTEED timestamp
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ----- Getters & Setters (unchanged) -----
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getHabitText() { return habitText; }
    public void setHabitText(String habitText) { this.habitText = habitText; }

    public String getDetectedCategory() { return detectedCategory; }
    public void setDetectedCategory(String detectedCategory) { this.detectedCategory = detectedCategory; }

    public int getEcoScore() { return ecoScore; }
    public void setEcoScore(int ecoScore) { this.ecoScore = ecoScore; }

    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }

    public Double getCarbonFootprint() { return carbonFootprint; }
    public void setCarbonFootprint(Double carbonFootprint) { this.carbonFootprint = carbonFootprint; }

    public String getBadge() { return badge; }
    public void setBadge(String badge) { this.badge = badge; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
