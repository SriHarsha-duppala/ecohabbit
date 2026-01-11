package com.ecohabit.eco_habit_nlp_system.dto;

public record ResetPasswordRequest(String token, String newPassword) {}
